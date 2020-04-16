package ro.uaic.info.myapp.dao;

public class Album {
    private String name;
    private int artist_id;
    private int release_year;

    public Album(String name, int artist_id, int release_year) {
        this.name = name;
        this.artist_id = artist_id;
        this.release_year = release_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
}
