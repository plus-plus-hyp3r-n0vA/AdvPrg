import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    private boolean readyToRead = false;
    private boolean exit = false;

    public boolean isExit() {
        return exit;
    }

    public boolean isReadyToRead() {
        return readyToRead;
    }

    public void startGame() throws IOException {

        // The server's IP address
        String serverAddress = "127.0.0.1";
        // The server's port
        int PORT = 8765;
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            socket.setSoTimeout(200);

            new GetStatusThread(this, in, out).start();

            // Send a request to the server
            Scanner scan = new Scanner(System.in);
            while (true) {
                try {
                    System.out.print("Input: ");
                    readyToRead = true;
                    // while waiting the client to do something, we can also read a status msg from server
                    String request = scan.nextLine();

                    if (request.equals("exit")) {
                        out.println("exit");
                        exit = true;
                        break;
                    }
                    out.println(request);

                    if (request.equals("stop")) break;

                    readyToRead = false;
                    Thread.sleep(400); // we make sure that GetStatusThread does not read anything from the server

                    String response = in.readLine();
                    System.out.println(response);

                } catch (SocketTimeoutException ignore) { }
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        var game = new GameClient();
        game.startGame();
    }

}
