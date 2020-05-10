import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.time.Instant;

public class ClientThread extends Thread {
    private Socket socket = null;
    private final GameServer gameServer;

    public ClientThread(Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

             PrintWriter out = new PrintWriter(socket.getOutputStream());
        ) {


            socket.setSoTimeout(1000);

            Player player = null;
            Game game = null;
            boolean success = false;
            Instant start = Instant.now();
            infiniteLoop:
            while (true) {
                try {

                    Instant end = Instant.now();
                    if(Duration.between(start, end).getSeconds() > 580) {out.println("exit connection"); break;}

                    String request = in.readLine();

                    start = end;

                    String[] tokens = request.split(" ");
                    System.out.printf("Received: %s\n", request);

                    switch (tokens[0]) {
                        case "create":
                            if (tokens.length == 3 && tokens[1].equals("game")) {
                                player = new Player("Player_" + gameServer.playerId, gameServer.playerId);
                                gameServer.gameList.add(game = new Game(tokens[2], player));
                                out.println(player.getId());
                                ++gameServer.playerId;
                            }
                            else
                                out.println("Error: Unknown command");
                            break;
                        case "join":
                            if (tokens.length == 3 && tokens[1].equals("game")) {
                                game = gameServer.gameList.stream()
                                        .filter(g -> g.getName().equals(tokens[2]))
                                        .findFirst().orElse(null);
                                if (game == null)
                                    out.println("Error: unknown game");
                                else {
                                    if (player == null)
                                        player = new Player("Player_" + gameServer.playerId, gameServer.playerId);
                                    game.addPlayer(player);
                                    ++gameServer.playerId;
                                    out.println(player.getId());
                                }
                            }
                            out.println("Error: Unknown command");
                            break;
                        case "submit":

                            if (tokens.length == 4 && tokens[1].equals("move")) {
                                try {
                                    if (game != null) {
                                        System.out.println("turn:" + game.getTurn());
                                        game.move(player.getId(), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                                        int playerId = game.gameOver();
                                        if(playerId == -1)
                                            out.println("Draw! No more moves!");
                                        else if(playerId == -2)
                                            out.println("Success");
                                        else
                                            out.println(String.format("The player %s won the game!",
                                                    game.getPlayerById(playerId)));
                                        success = false; // we will show the board if it's his turn
                                    }else
                                        out.println("Error: You didn't join a game yet!");
                                } catch (InvalidMoveException e) {
                                    out.println(e.getMessage());
                                }
                            } else
                                out.println("Error: Unknown command");
                            break;
                        case "start":
                            if (tokens.length == 2 && tokens[1].equals("game")) {
                                if (game != null) {
                                    String result = game.start(player);
                                    if(result.equals("1"))
                                        out.println("Success");
                                    else
                                        out.println(result);
                                } else
                                    out.println("Error: You didn't join a game yet!");
                            }
                            else
                                out.println("Error: Unknown command");

                            break;
                        case "stop":
                            out.println("Server stopped!");
                            out.flush();
                            gameServer.Stop();
                            break infiniteLoop;
                        case "exit":
                            break infiniteLoop;
                        default:
                            out.println("Error: Unknown command");
                    }
                    out.flush();
                } catch (SocketTimeoutException e) {
                    if(!success && game != null && game.isStarted() && game.getTurn() + 1 == player.getId()) {
                        System.out.println(player.getName());
                        Thread.sleep(200);
                        out.printf("Your turn!\nThe board:\n%s",game.getBoard());
                        out.flush();
                        start = Instant.now();
                        while (!success) {
                            try {
                                Instant end = Instant.now();
                                if(Duration.between(start, end).getSeconds() > 580) break infiniteLoop;

                                String request = in.readLine();

                                System.out.println("SocketTimeoutException: " + request);

                                if (request.equals("Ok"))
                                    success = true;
                            } catch (SocketTimeoutException ignore) {}
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
