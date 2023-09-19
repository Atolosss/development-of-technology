package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.TemperatureHistory;
import http.utils.PgConnectionUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TemperatureHistoryRepository implements CrudRepository<TemperatureHistory, Long> {

    @Override
    public Optional<TemperatureHistory> findById(final Long id) {
        String sql = "SELECT * FROM temperaturehistory WHERE id = " + id;
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                TemperatureHistory temperatureHistory = TemperatureHistory.builder()
                        .id(resultSet.getLong("id"))
                        .city(resultSet.getString("city"))
                        .temperature(resultSet.getBigDecimal("temperature"))
                        .createDateTime(resultSet.getTimestamp("create_date_time")
                                .toLocalDateTime())
                        .build();
                return Optional.ofNullable(temperatureHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(final TemperatureHistory temperatureHistory) {
        String sql = "INSERT INTO temperaturehistory (id, city, temperature, create_date_time) VALUES (?, ?, ?, ?)";
        try (Connection connection = PgConnectionUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, temperatureHistory.getId());
            statement.setString(2, temperatureHistory.getCity());
            statement.setBigDecimal(3, temperatureHistory.getTemperature());
            statement.setTimestamp(4, Timestamp.valueOf(temperatureHistory.getCreateDateTime()));
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(final Long id) {
        String sql = "DELETE FROM temperaturehistory WHERE id = " + id;
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TemperatureHistory> findAll() {
        String sql = "SELECT * FROM temperaturehistory";
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {
            List<TemperatureHistory> temperatureHistories = new ArrayList<>();
            while (resultSet.next()) {
                var temperatureHistory = TemperatureHistory.builder()
                        .id(resultSet.getLong("id"))
                        .city(resultSet.getString("city"))
                        .temperature(resultSet.getBigDecimal("temperature"))
                        .createDateTime(resultSet.getTimestamp("create_date_time")
                                .toLocalDateTime())
                        .build();
                temperatureHistories.add(temperatureHistory);
            }
            return temperatureHistories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getMaxId() {
        String sql = "INSERT INTO temperaturehistory(city,temperature) VALUES ('lol',0) RETURNING id";

        try (Connection connection = PgConnectionUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                return 0L;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

