package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.CityHistory;
import http.utils.PgConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityHistoryRepository implements CrudRepository<CityHistory, Integer> {

    @Override
    public Optional<CityHistory> findById(Integer id) {
        String sql = "SELECT * FROM cityHistory WHERE id_city = " + id;
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                CityHistory cityHistory = CityHistory.builder()
                        .idCity(resultSet.getInt("id_city"))
                        .city(resultSet.getString("city"))
                        .temperature(resultSet.getBigDecimal("temperature"))
                        .build();
                return Optional.ofNullable(cityHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(CityHistory cityHistory) {
        String sql = "INSERT INTO cityHistory(id_city, city, temperature) VALUES (?, ?, ?)";
        try (Connection connection = PgConnectionUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cityHistory.getIdCity());
            statement.setString(2, cityHistory.getCity());
            statement.setBigDecimal(3, cityHistory.getTemperature());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {

        }
    }


    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM cityHistory WHERE id_city = " + id;
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CityHistory> findAll() {
        String sql = "SELECT * FROM cityHistory";
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {
            List<CityHistory> cityHistories = new ArrayList<>();
            while (resultSet.next()) {
                var cityHistory = CityHistory.builder()
                        .idCity(resultSet.getInt("id_city"))
                        .city(resultSet.getString("city"))
                        .temperature(resultSet.getBigDecimal("temperature"))
                        .build();
                cityHistories.add(cityHistory);
            }
            return cityHistories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMaxId() {
        String sql = "INSERT INTO temperaturehistory(city,temperature) VALUES ('lol',0) RETURNING id";

        try (Connection connection = PgConnectionUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findByCity(String city) {
        String sql = "SELECT * FROM cityHistory WHERE city = ?";
        try (Connection connection = PgConnectionUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CityHistory cityHistory = CityHistory.builder()
                            .idCity(resultSet.getInt("id_city"))
                            .city(resultSet.getString("city"))
                            .temperature(resultSet.getBigDecimal("temperature"))
                            .build();
                    return cityHistory.getIdCity();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
