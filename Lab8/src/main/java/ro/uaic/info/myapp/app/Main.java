package ro.uaic.info.myapp.app;

import com.github.javafaker.Faker;
import ro.uaic.info.myapp.dao.*;

import java.util.*;

public class Main {

    public static void generateRandomData() {
        Faker faker = new Faker();
        Random rand = new Random();

        Dao<Artist> artistDao = new ArtistDao();
        Dao<Album> albumDao = new AlbumDao();
        Dao<Chart> chartDao = new ChartDao();

        List<Integer> albumsIds = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            try {
                Integer artist_id = artistDao.create(new Artist(faker.artist().name(), faker.country().name()));
                for (int j = 0; j < rand.nextInt(15) + 5; ++j) {
                    albumsIds.add(albumDao.create(new Album(faker.lorem().word(), artist_id,
                            2020 - rand.nextInt(50))));
                }
            } catch (DbException e) {
                System.out.println(e.getMessage());
            }
        }
        try { //provizoriu din cauza erorii multiplelor cursoare deschise
            Database.closeConnection();
            Database.getConnection();
        }catch (Exception ignore){}
        for (int i = 0; i < 7; ++i) {
            try {
                LinkedList<Integer> albums = new LinkedList<>();
                for (int j = 0; j < rand.nextInt(25) + 5; ++j)
                    albums.add(albumsIds.get(rand.nextInt(albumsIds.size())));
                chartDao.create(new Chart(faker.lorem().word(), albums));
            } catch (DbException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    public static ArrayList<Artist> getArtistsRank(LinkedList<Chart> charts, HashMap<Integer, Integer> albumArtistMap) {
        Map<Integer, Integer> rank = new HashMap<>();
        for (var chart : charts) {
            int i = 0;
            for (Integer albumId : chart.getAlbumRank()) {
                Integer artistId = albumArtistMap.get(albumId);
                Integer rankPoints = chart.getAlbumRank().size() - i;
                if (rank.containsKey(artistId))
                    rank.put(artistId, rank.get(artistId) + rankPoints);
                else
                    rank.put(artistId, rankPoints);
                ++i;
            }
        }
        List<ArtistAndRank> list = new ArrayList<>();
        for (var entry : rank.entrySet()) {
            list.add(new ArtistAndRank(entry.getKey(), entry.getValue()));
        }
        list.sort(Comparator.comparing(o -> o.rank));
        ArrayList<Artist> result = new ArrayList<>();
        ArtistDao artistDao = new ArtistDao();
        for (var arR : list) {
            try {
                result.add(artistDao.findById(arR.artistId));
            } catch (DbException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    public static HashMap<Integer, Integer> makeAlbumArtistMap(LinkedList<Chart> charts) {
        HashMap<Integer, Integer> albumArtistMap = new HashMap<>();
        AlbumDao albumDao = new AlbumDao();
        for (var chart : charts) {
            int i = 0;
            for (Integer albumId : chart.getAlbumRank()) {
                try {
                    albumArtistMap.put(albumId, albumDao.findById(albumId).getArtist_id());
                } catch (DbException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return albumArtistMap;
    }

    public static void main(String[] args) throws DbException {
        generateRandomData();
        ChartDao chartDao = new ChartDao();
        try {
            Database.closeConnection();
            Database.getConnection();
        }catch (Exception ignore){}
        LinkedList<Chart> charts = chartDao.getAllCharts();
        List<Artist> rank = getArtistsRank(charts, makeAlbumArtistMap(charts));
        int i = 0;
        for (Artist a : rank) {
            System.out.printf("%d : ", i);
            System.out.println(a);
            ++i;
        }
        try {
            Database.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
//        Ex1:

//        try {
//            ArtistController arC = new ArtistController();
//            AlbumController alC = new AlbumController();
//
////            arC.create("Yulescu", "CCC");
////            alC.create("Albumul albumelor", 7, 2114);
//
//            Integer Y = arC.findByName("Yulescu");
//            if(Y==null)
//                System.out.println("Didn't found Yulescu");
//            else {
//                System.out.println(Y.intValue());
//
//                Integer artist1 = alC.findByArtist(Y)[0];
//                if (artist1 == null)
//                    System.out.println("Didn't found album with this artist_id");
//                else
//                    System.out.println(artist1.intValue());
//            }
//            Database.closeConnection();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }
}
