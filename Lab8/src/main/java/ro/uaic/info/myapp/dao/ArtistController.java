package ro.uaic.info.myapp.dao;

import java.sql.*;

public class ArtistController {

    public void create(String name, String country) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO artists(name, country) VALUES(?,?)");
        stmt.setString(1,name);
        stmt.setString(2,country);
        stmt.executeQuery();
    }

    public Integer findByName(String name) throws SQLException{
        Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM artists WHERE name=?");
        stmt.setString(1,name);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int x = rs.getInt("id");
            try { rs.close(); } catch (Exception ignore) { }
            return x;
        }
        try { rs.close(); } catch (Exception ignore) { }
        return null;
    }
}
