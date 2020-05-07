import java.util.ArrayList;
import java.util.List;

public class Game {
    private final String name; // unique id
    private final Board board;
    private final List<Player> players;
    private int turn;
    private boolean started;
    //    private int id_player_counter;

    public Game(String name, Player player) {
        turn = 0;
        started = false;
        this.name = name;
        board = new Board();
        players = new ArrayList<>();
        players.add(player);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerById(int playerId) {
        return players.stream()
                .filter(p -> p.getId() == playerId)
                .findFirst().orElse(null);
    }

    public String start(Player player) {
        if(players.size() < 2)
            return "Error: To few players!";
//        turn = (turn + 1) % players.size(); // TO DO: de schimbat cu o noua variabila pentru turn
        started = true;
        board.reset();
        return "1";
    }

    public void move(int id, int i, int j) throws InvalidMoveException {
        if(!started)
            throw new InvalidMoveException("Error: The game is not started");
        if(id != turn + 1)
            throw new InvalidMoveException("Error: Now is the turn of " + players.stream()
                    .filter(p -> p.getId() == turn+1)
                    .findFirst().orElse(null)); //TO DO: "STRING" + null
        board.put(id, i, j);
        turn = (turn + 1) % players.size();
    }

    private boolean chainHorizontalRow(int i, int j) {
        int val = board.getCell(i, j);
        if(val==0)
            return false;
        for(int x = 1; x < 5; ++x)
            if(board.getCell(i, j + x) != val)
                return false;
        return true;
    }

    private boolean chainVerticalRow(int i, int j) {
        int val = board.getCell(i, j);
        if(val==0)
            return false;
        for(int x = 1; x < 5; ++x)
            if(board.getCell(i + x, j) != val)
                return false;
        return true;
    }

    private boolean chainDiagonal1(int i, int j) {
        int val = board.getCell(i, j);
        if(val==0)
            return false;
        for(int x = 1; x < 5; ++x)
            if(board.getCell(i + x, j + x) != val)
                return false;
        return true;
    }

    private boolean chainDiagonal2(int i, int j) {
        int val = board.getCell(i, j);
        if(val==0)
            return false;
        for(int x = 1; x < 5; ++x)
            if(board.getCell(i - x, j - x) != val)
                return false;
        return true;
    }

    public int gameOver() {
        int idPlayer;
        int noMoves = 1;
        for(int i=0; i<board.size(); ++i)
            for(int j=0; j<board.size(); ++j) {
                idPlayer = board.getCell(i, j);
                noMoves &= idPlayer;
                if(chainHorizontalRow(i, j) || chainVerticalRow(i, j) || chainDiagonal1(i, j) || chainDiagonal2(i, j)){
                    started = false; // the game is over and idPlayer won
                    return idPlayer;
                }
            }
        if(noMoves != 0) {
            started = false;
            return -1; // no more moves
        }
        return -2; // the game isn't over yet
    }

    public boolean isStarted() {
        return started;
    }

    public int getTurn() {
        return turn;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
