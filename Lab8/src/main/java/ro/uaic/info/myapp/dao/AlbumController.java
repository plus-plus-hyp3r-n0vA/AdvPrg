package ro.uaic.info.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    public void create(String name, int artistId, int releaseYear) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO albums(name, artist_id, release_year) " +
                "VALUES(?,?,?)");
        stmt.setString(1, name);
        stmt.setInt(2, artistId);
        stmt.setInt(3, releaseYear);
        stmt.executeQuery();
    }

    public Integer[] findByArtist(int artistId) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM albums WHERE artist_id=?");
        stmt.setInt(1,artistId);
        ResultSet rs = stmt.executeQuery();
        List<Integer> idList = new ArrayList<>();
        while (rs.next())
            idList.add(rs.getInt("id"));
        try { rs.close(); } catch (Exception ignore) { }
        return (Integer[]) idList.toArray();
    }
}
