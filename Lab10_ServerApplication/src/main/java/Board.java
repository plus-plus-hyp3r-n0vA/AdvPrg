import java.util.Arrays;

public class Board {
    private final int[][] board;

    public Board() {
        this.board = new int[15][15];
    }

    public int size() {
        return board.length;
    }

    public int getCell(int i, int j) {
        if(i < 0 || i >= board.length || j < 0 || j >= board.length)
            return -1;
        return board[i][j];
    }

    public void put(int id, int i, int j) throws InvalidMoveException {
        if(i < 0 || i >= board.length || j < 0 || j >= board.length)
            throw new InvalidMoveException("Error: Move outside the board!");
        if(board[i][j] != 0)
            throw new InvalidMoveException("Error: The cell is taken");
        board[i][j] = id;
    }

    public void reset() {
        for (int[] line : board) Arrays.fill(line, 0);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int[] row : board) {
            for (int j = 0; j < board.length; ++j)
                str.append(String.format("%d ", row[j]));
            str.append('\n');
        }
        return str.toString();
    }
}
