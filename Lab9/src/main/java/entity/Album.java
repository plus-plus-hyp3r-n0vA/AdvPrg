package entity;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "findByAlbumName", query = "SELECT a FROM Album a WHERE a.name = :name"),
        @NamedQuery(name = "findByArtistId", query = "SELECT a FROM Album a WHERE a.artistId = :artist_id")
})
@Entity
@Table(name = "ALBUMS", schema = "DBA_IP")
public class Album {
    private long id;
    private String name;
    private long artistId;
    private Long releaseYear;

    public Album() {

    }

    public Album(String name, long artistId, Long releaseYear) {
        this.name = name;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ARTIST_ID")
    public long getArtistId() {return artistId;}

    public void setArtistId(long artistId) {this.artistId = artistId;}

    @Basic
    @Column(name = "RELEASE_YEAR")
    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album that = (Album) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(releaseYear, that.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseYear);
    }
}
