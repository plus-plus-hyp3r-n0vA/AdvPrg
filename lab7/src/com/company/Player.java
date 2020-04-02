package com.company;

import java.util.LinkedList;
import java.util.List;

public class Player implements Runnable {
    private String name;
    private Board board;
    private List<Token> myTokens;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", myTokens=" + myTokens +
                '}';
    }

    @Override
    public void run() {
        Token t;
        while(true){
            try {
                t = board.getToken();
                myTokens.add(t);
            }catch (Exception e)
            {
                break;
            }
        }
    }
}
