package ro.uaic.info.myapp.dao;

import java.sql.*;

public class Database {
    private static Connection sql_con;

    private Database() {}

    public static Connection getConnection() throws SQLException {
        if(sql_con == null || sql_con.isClosed())
            sql_con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                                                 "dba_ip", "sql");
        return sql_con;
    }

    public static void closeConnection() throws SQLException {
        sql_con.close();
    }

}
