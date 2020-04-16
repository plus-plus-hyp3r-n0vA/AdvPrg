package ro.uaic.info.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AlbumDao implements Dao<Album> {

    @Override
    public Integer create(Album album) throws DbException {
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO albums(name, artist_id, release_year) " +
                    "VALUES(?,?,?)", new String[] {"id"});
            stmt.setString(1, album.getName());
            stmt.setInt(2, album.getArtist_id());
            stmt.setInt(3, album.getRelease_year());
            stmt.executeQuery();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                int x = rs.getInt(1);
                try { rs.close(); } catch (Exception ignore) { }
                return x;
            }
            try { rs.close(); } catch (Exception ignore) { }
            return null;
        } catch (SQLException e) {
            throw new DbException("Error when creating album!", e);
        }
    }

    @Override
    public Album findById(int id) throws DbException {
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM albums WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Album a = new Album(rs.getString("name"), rs.getInt("artist_id"),
                        rs.getInt("release_year"));
                try { rs.close(); } catch (Exception ignore) { }
                return a;
            }
            try { rs.close(); } catch (Exception ignore) { }
            return null;
        } catch (SQLException e) {
            throw new DbException("Error trying to find album by id!", e);
        }
    }

    public LinkedList<Album> findByArtist(int artistId) throws DbException {
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM albums WHERE artist_id=?");
            stmt.setInt(1,artistId);
            ResultSet rs = stmt.executeQuery();
            var albumList = new LinkedList<Album>();
            while (rs.next()) {
                albumList.add(new Album(rs.getString("name"), rs.getInt("artist_id"),
                        rs.getInt("release_year")));
            }
            try { rs.close(); } catch (Exception ignore) { }
            return albumList;
        } catch (SQLException e) {
            throw new DbException("Error trying to find album by artist!", e);
        }
    }
}
