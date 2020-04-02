package com.company;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int m;
    private List<Token> list;

    public Board(int n, int m) throws Exception {
        if (n > m)
            throw new Exception("Invalid Board");
        list = new LinkedList<>();
        for (int i = 1; i <= m && i <= n; ++i)
            list.add(new Token(i));
    }

    public List<Token> getList() {
        return list;
    }

    public synchronized Token getToken() throws Exception {
        if(list.size() > 0){
            Token t = list.get(0);
//            System.out.println(t.getNumber());
            list.remove(0);
            return t;
        }
        else
            throw new Exception("Nu mai exista tokenuri");
    }
}
