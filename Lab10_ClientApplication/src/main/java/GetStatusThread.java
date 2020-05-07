import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;

public class GetStatusThread extends Thread
{
    private GameClient gameClient;
    private BufferedReader in;
    private PrintWriter out;

    GetStatusThread(GameClient gameClient, BufferedReader in, PrintWriter out) {
        this.gameClient = gameClient;
        this.in = in;
        this.out = out;
    }

    public void run() {
        while(!gameClient.isExit()){
            try {
                if(gameClient.isReadyToRead()) {
                    String myTurnMsg = in.readLine();
                    System.out.println(myTurnMsg);
                    out.println("Ok");
                }
            }
            catch (SocketTimeoutException ignore) {}
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
