package ro.uaic.info.myapp.dao;

import java.util.LinkedList;

public class Chart {
    private String name;
    private LinkedList<Integer> albumRank;

    public Chart(String name, LinkedList<Integer> albumRank) {
        this.name = name;
        this.albumRank = albumRank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Integer> getAlbumRank() {
        return albumRank;
    }

    public void setAlbumRank(LinkedList<Integer> albumRank) {
        this.albumRank = albumRank;
    }
}
