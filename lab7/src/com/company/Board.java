package com.company;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int m, n;
    private int sizek;
    private List<Token> list;

    public Board(int n, int m, int sizek) throws Exception {
        if (n > m)
            throw new Exception("Invalid Board");

        this.sizek = sizek;
        this.n = n;
        this.m = m;
        list = new LinkedList<>();

        resetBoard();
    }

    public void resetBoard(){
        for (int i = 1; i <= m && i <= n; ++i)
            list.add(new Token(i));
    }

    public List<Token> getList() {
        return list;
    }


    public synchronized Token getToken(Token lastToken) throws Exception {
        if(list.size() == 0)
            throw new Exception("Nu mai exista tokenuri");
        for(Token t : list)
            if(lastToken == null || t.getNumber() - lastToken.getNumber() == sizek) {
                list.remove(t);
                return t;
            }
        throw new Exception("Nu mai exista tokenuri care sa respecte progresia");
    }


    public boolean empty(){
        return list.size() == 0;
    }
}
