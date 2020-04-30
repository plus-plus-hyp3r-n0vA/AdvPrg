import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket = null ;
    private GameServer gameServer;
    public ClientThread (Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
    }

    public void run () {
        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true) {
                String request = in.readLine();

                System.out.printf("Received: %s\n", request);

                if (request.equals("stop")) {
                    out.println("Server stopped!");
                    out.flush();
                    gameServer.Stop();
                    break;
                } if(request.equals("exit"))
                    break;
                else {
                    out.println("Server received the request...");
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
