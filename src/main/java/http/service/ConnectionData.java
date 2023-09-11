package http.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionData {
    public static final String DB = "example";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB;
    public static final String USER = "postgres";
    public static final String PASS = "12345";
    static final String DRIVER = "org.postgresql.Driver";
    private Connection connection;

    public void DatabaseConnection() {
        try {
            Class.forName(DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

