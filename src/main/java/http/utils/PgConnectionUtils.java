package http.utils;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class PgConnectionUtils {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/example";
    static final String USER = "igorsimakov";
    static final String PASS = "admin";

    //TODO: read from ReadPropertiesUtils
    public static Connection getConnection() {
        try {
            var connection = DriverManager.getConnection(DB_URL, USER, PASS);

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
