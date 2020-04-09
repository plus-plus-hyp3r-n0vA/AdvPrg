package com.company;

import java.util.LinkedList;
import java.util.List;

public class Player implements Runnable {
    private String name;
    private Board board;
    private LinkedList<Token> myTokens = new LinkedList<>();

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
        while (true) {
            try {
                Token t;
                t = board.getToken(myTokens.size()!=0 ? myTokens.getLast() : null);
                myTokens.add(t);
            } catch (Exception e) {
                break;
            }
        }
    }
}
