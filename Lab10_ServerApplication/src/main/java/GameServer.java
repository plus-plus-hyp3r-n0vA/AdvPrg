import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GameServer {
    public static final int PORT = 8765;
    public static boolean serverRunning = true;

    public GameServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(5000);
            while (serverRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    new ClientThread(socket, this).start();
                } catch (SocketTimeoutException ignore) {}
            }
        }
    }

    public void Stop() {
        GameServer.serverRunning = false;
    }
}
