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
            if(gameClient.isReadyToRead()) {
                try {
                    String myTurnMsg = in.readLine();
                    if (myTurnMsg.equals("Your turn!")) {
                        StringBuilder sb = new StringBuilder(myTurnMsg);
                        while (true) {
                            try {
                                myTurnMsg = in.readLine();
                                sb.append(myTurnMsg).append('\n');
                            } catch (SocketTimeoutException e) {
                                break;
                            }
                        }
                        System.out.println(sb.toString());
                    } else {
                        System.out.print(myTurnMsg);
                    }
                    System.out.flush();

                    out.println("Ok");
                    out.flush();
                }
                catch (SocketTimeoutException ignore) {}
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
