import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    private boolean readyToRead = true;
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
            BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

//            Scanner scan = new Scanner(System.in);
            while (true) {
                try {
                    System.out.print("Input: ");
                    // while waiting the client to do something, we can also read a status msg from server
                    String request = scan.readLine();

                    if (request.equals("exit")) {
                        out.println("exit");
                        exit = true;
                        break;
                    }

                    if (request.equals("stop")) break;
                    readyToRead = false;

                    Thread.sleep(400); // we make sure that GetStatusThread does not read anything from the server

                    out.println(request);
                    out.flush();

                    String response = in.readLine();
                    readyToRead = true;
                    System.out.println("main thread resp:" + response);

                } catch (SocketTimeoutException ignore) { }
            }
            scan.close();
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
