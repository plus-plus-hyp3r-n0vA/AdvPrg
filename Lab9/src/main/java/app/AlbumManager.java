package app;


import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;


public class AlbumManager {
    public static void main(String[] args) {
        var emf = PersistenceUtil.getEntityManagerFactory();
        try(var artistRepository = new ArtistRepository(emf.createEntityManager());
            var albumRepository = new AlbumRepository(emf.createEntityManager()))
        {
            artistRepository.create(new Artist("Bob Bob", "Romania"));
            var artist = artistRepository.findByName("Bob Bob").get(0);
            System.out.printf("Id\tName\tCountry\n%d\t%s\t%s\n",
                    artist.getId(), artist.getName(), artist.getCountry());

            albumRepository.create(new Album("Albumul secolului", artist.getId(), 2020L));
            var album = albumRepository.findByArtist(artist).get(0);
            System.out.printf("Id\t\tName\t\t\t\tArtist_id\tRelease_year\n%d\t%s\t%d\t\t\t%d\n",
                    album.getId(), album.getName(), album.getArtistId(), album.getReleaseYear());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        emf.close();
    }
}
