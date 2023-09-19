package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.CityHistory;
import http.utils.PgConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityHistoryRepository implements CrudRepository<CityHistory, Long> {

    private static CityHistory extractCityHistory(final ResultSet resultSet) throws SQLException {
        return CityHistory.builder()
                .id(resultSet.getLong("id"))
                .city(resultSet.getString("city"))
                .build();
    }

    @Override
    public Optional<CityHistory> findById(final Long id) {
        String sql = "SELECT * FROM cityHistory WHERE id = ?";

        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return Optional.ofNullable(extractCityHistory(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public CityHistory save(final CityHistory cityHistory) {
        String sql = "INSERT INTO cityHistory(city) VALUES (?)";

        try (Connection connection = PgConnectionUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cityHistory.getCity());
            statement.executeUpdate();

            cityHistory.setId(PgConnectionUtils.getGeneratedKeys(statement));

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return cityHistory;
    }


    @Override
    public void delete(final Long id) {
        String sql = "DELETE FROM cityHistory WHERE id = ?";
        try (var connection = PgConnectionUtils.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
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
                cityHistories.add(extractCityHistory(resultSet));
            }
            return cityHistories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
