package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private String[] players_name;
    private List<Player> players = new ArrayList<>();

    public Game(Board board, String[] players) {
        this.board = board;
        this.players_name = players;
    }

    public void Start() {
        for (String s : players_name) {
            players.add(new Player(s, board));
            Runnable runnable = players.get(players.size()-1);
            new Thread(runnable).start();
        }
    }

    public void showPlayersTokens() {
        for(Player p : players)
            System.out.println(p);
    }
}
