package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static EntityManagerFactory emf;

    private PersistenceUtil() { }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen())
            emf = Persistence.createEntityManagerFactory("MusicAlbumsPU");
        return emf;
    }
}
