package repo;

import entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class ArtistRepository implements AutoCloseable {
    private final EntityManager entityManager;

    public ArtistRepository(EntityManager em) {
        entityManager = em;
    }

    public void create(Artist artist) {
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
    }

    public Artist findById(long id) {
        return entityManager.find(Artist.class, id);
    }

    public List<Artist> findByName(String name) {
        return entityManager.createNamedQuery("findByArtistName")
                .setParameter("name", name).getResultList();
    }

    @Override
    public void close() {
        entityManager.close();
    }
}
