package ro.uaic.info.myapp.app;

public class ArtistAndRank {
    public Integer artistId;
    public Integer rank;

    ArtistAndRank(Integer artistId, Integer rank) {
        this.artistId = artistId;
        this.rank = rank;
    }

    public int compareTo(ArtistAndRank arR) {
        return this.rank.compareTo(arR.rank);
    }
}
