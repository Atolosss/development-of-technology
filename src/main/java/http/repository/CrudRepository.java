package http.repository;

import java.math.BigDecimal;
import java.sql.*;

public class CrudRepository {
    static final String DB = "example";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB;
    static final String USER = "postgres";
    static final String PASS = "12345";
    static final String DRIVER = "org.postgresql.Driver";
    static final String TABLE = "WHEATHER";
    private Connection connection;

    public void DatabaseConnection() {
        try {
            Class.forName(DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createRecord(String name, float temp) {
        try {
            String sql = "INSERT INTO " + TABLE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, name);
            preparedStatement.setFloat(3, temp);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String readRecord(int id) {
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE number_query = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Номер запроса:");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Не получилось!";
    }

    public void getAllRecords() {
        try {
            String sql = "SELECT * FROM " + TABLE;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("number_query");
                String englishName = resultSet.getString("english_name");
                BigDecimal temperatureC = resultSet.getBigDecimal("temperature_c");
                Timestamp timestamp = resultSet.getTimestamp("date");

                System.out.println("Номер запроса: " + id);
                System.out.println("Город: " + englishName);
                System.out.println("Погода: " + temperatureC);
                System.out.println("Время создания: " + timestamp);
                System.out.println();
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
