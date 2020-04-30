import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            var gameServer = new GameServer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
