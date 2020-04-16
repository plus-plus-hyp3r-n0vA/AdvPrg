package ro.uaic.info.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ArtistDao implements Dao<Artist> {

    @Override
    public Integer create(Artist artist) throws DbException {
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO artists(name, country) VALUES(?,?)",
                    new String[] {"id"});
            stmt.setString(1, artist.getName());
            stmt.setString(2, artist.getCountry());
            stmt.executeQuery();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                int x =  rs.getInt(1);
                try { rs.close(); } catch (Exception ignore) { }
                return x;
            }
            try { rs.close(); } catch (Exception ignore) { }
            return null;
        } catch (SQLException e) {
            throw new DbException("Error when creating artist!", e);
        }
    }

    @Override
    public Artist findById(int id) throws DbException {
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM artists WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Artist a = new Artist(rs.getString("name"), rs.getString("country"));
                try { rs.close(); } catch (Exception ignore) { }
                return a;
            }
            try { rs.close(); } catch (Exception ignore) { }
            return null;
        } catch (SQLException e) {
            throw new DbException("Error trying to find Artist by id!", e);
        }
    }

    public LinkedList<Artist> findByName(String name) throws DbException {
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM artists WHERE name=?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            var artistList = new LinkedList<Artist>();
            while (rs.next()) {
                artistList.add(new Artist(rs.getString("name"),
                        rs.getString("country")));
            }
            try { rs.close(); } catch (Exception ignore) { }
            return artistList;
        } catch (SQLException e) {
            throw new DbException("Error trying to find Artist by name!", e);
        }
    }
}
