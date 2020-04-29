package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumRepository implements AutoCloseable{
    private final EntityManager entityManager;

    public AlbumRepository(EntityManager em) {
        entityManager = em;
    }

    public void create(Album album) {
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
    }

    public Album findById(long id) {
        return entityManager.find(Album.class, id);
    }

    public List<Album> findByName(String name) {
        return entityManager.createNamedQuery("findByAlbumName")
                .setParameter("name", name).getResultList();
    }

    public List<Album> findByArtist(Artist artist) {
        return entityManager.createNamedQuery("findByArtistId")
                .setParameter("artist_id", artist.getId()).getResultList();
    }

    @Override
    public void close() {
        entityManager.close();
    }
}
