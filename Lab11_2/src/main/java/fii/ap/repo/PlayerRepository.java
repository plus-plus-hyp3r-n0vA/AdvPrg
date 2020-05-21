package fii.ap.repo;

import fii.ap.entity.Game;
import fii.ap.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> { }

//public interface PlayerRepository extends JpaRepository<Player, Integer> { }
//public class PlayerRepository implements AutoCloseable{
//    private final EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
//
//    public void save(Player player) {
//        entityManager.getTransaction().begin();
//        entityManager.persist(player);
//        entityManager.getTransaction().commit();
//    }
//
//    public Player findById(long id) {
//        return entityManager.find(Player.class, id);
//    }
//
//    public List<Player> findAll() {
//        return entityManager.createNamedQuery("findAll").getResultList();
//    }
//
//    @Override
//    public void close() {
//        entityManager.close();
//    }
//}