package http.utils;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class PgConnectionUtils {
    private static final SessionFactory BUILDSESSIONFACTORY = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new RuntimeException();
        }
    }

    public static Connection getConnection() {
        try {
            var url = ReadPropertiesUtils.getProperty("db_url");
            var user = ReadPropertiesUtils.getProperty("user");
            var pass = ReadPropertiesUtils.getProperty("pass");
            var connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getBuildsessionfactory() {
        return BUILDSESSIONFACTORY;
    }


}

