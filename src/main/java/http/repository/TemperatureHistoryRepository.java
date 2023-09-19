package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.TemperatureHistory;
import http.utils.PgConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TemperatureHistoryRepository implements CrudRepository<TemperatureHistory, Long> {

    private static TemperatureHistory extractTemperatureHistory(ResultSet resultSet) throws SQLException {
        return TemperatureHistory.builder()
                .id(resultSet.getLong("id"))
                .temperature(resultSet.getBigDecimal("temperature"))
                .createDateTime(resultSet.getTimestamp("create_date_time")
                        .toLocalDateTime())
                .build();
    }

    @Override
    public Optional<TemperatureHistory> findById(final Long id) {
        String sql = "SELECT * FROM temperaturehistory WHERE id = ?";
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(extractTemperatureHistory(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public TemperatureHistory save(final TemperatureHistory temperatureHistory) {
        String sql = "INSERT INTO temperaturehistory (temperature, create_date_time,city_id) VALUES (?, ?, ?)";

        try (Connection connection = PgConnectionUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setBigDecimal(1, temperatureHistory.getTemperature());
            statement.setTimestamp(2, Timestamp.valueOf(temperatureHistory.getCreateDateTime()));
            statement.setLong(3,temperatureHistory.getCityId());

            statement.executeUpdate();
            temperatureHistory.setId(PgConnectionUtils.getGeneratedKeys(statement));

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temperatureHistory;
    }

    @Override
    public void delete(final Long id) {
        String sql = "DELETE FROM temperaturehistory WHERE id = ?";
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1,id);

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
                temperatureHistories.add(extractTemperatureHistory(resultSet));
            }
            return temperatureHistories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

