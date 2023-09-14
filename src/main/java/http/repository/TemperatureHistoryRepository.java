package http.repository;

import crud.repository.CrudRepository;
import http.model.entity.TemperatureHistory;
import http.utils.PgConnectionUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TemperatureHistoryRepository implements CrudRepository<TemperatureHistory, Long> {

//    public void createRecord(String name, float temperature) {
//        try {
//            String sql = "INSERT INTO wheather ()" + TABLE;
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(2, name);
//            preparedStatement.setFloat(3, temperature);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public String readRecord(int id) {
//        try {
//            String sql = "SELECT * FROM " + TABLE + " WHERE number_query = " + id;
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getString("Номер запроса:");
//            }
//            resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "Не получилось!";
//    }

    //ToDO
    @Override
    public Optional<TemperatureHistory> findById(final Long id) {
        return Optional.empty();
    }

    //ToDO
    @Override
    public void save(final TemperatureHistory temperatureHistory) {

    }

    //ToDO
    @Override
    public void delete(final Long id) {

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
}
