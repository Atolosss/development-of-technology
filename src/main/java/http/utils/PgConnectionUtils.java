package http.utils;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@UtilityClass
public class PgConnectionUtils {

    public static Connection getConnection() {
        try {
            var url = ReadPropertiesUtils.getProperty("db_url");
            var user = ReadPropertiesUtils.getProperty("user");
            var pass = ReadPropertiesUtils.getProperty("pass");
            DriverManager.getConnection(url,user,pass);
//            connection.setAutoCommit(false);
//            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//

    }

    public static Long getGeneratedKeys(final PreparedStatement preparedStatement) {
        try {
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong("id");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
