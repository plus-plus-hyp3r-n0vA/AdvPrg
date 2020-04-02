package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            Board board = new Board(4, 4);
            Game g = new Game(board, new String[]{"p1", "p2", "p3"});
            g.Start();
            while (board.getList().size() != 0) {}
            g.showPlayersTokens();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
