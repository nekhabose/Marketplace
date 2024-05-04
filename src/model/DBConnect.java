package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/marketplace";
    private static final String USER = "root";
    private static final String PASS = "nekha@071098";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
