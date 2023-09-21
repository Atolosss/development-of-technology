package http;

import com.fasterxml.jackson.databind.ObjectMapper;
import http.client.AccuweatherClient;
import http.mapper.AccuweatherMapper;
import http.repository.CityHistoryRepository;
import http.repository.TemperatureHistoryRepository;
import http.service.AccuweatherService;
import http.utils.PgConnectionUtils;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;

import java.sql.SQLException;

public class App {

    @SneakyThrows
    public static void main(final String[] args) {
        Class.forName("org.postgresql.Driver");
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        AccuweatherClient accuweatherClient = new AccuweatherClient(okHttpClient, objectMapper);
        TemperatureHistoryRepository temperatureHistoryRepository = new TemperatureHistoryRepository();
        CityHistoryRepository cityHistoryRepository = new CityHistoryRepository();
        AccuweatherService accuweatherService = new AccuweatherService(
                accuweatherClient, new AccuweatherMapper(), temperatureHistoryRepository, cityHistoryRepository);

        initDb();
        


        accuweatherService.run();


    }

    public static void initDb() {
        try (var connection = PgConnectionUtils.getConnection()) {
            var statement = connection.createStatement();
            String createTemperatureHistory = """
                    CREATE TABLE IF NOT EXISTS cityHistory
                         (
                             id               serial primary key,
                             city             varchar(40) unique not null
                         );
                         CREATE TABLE IF NOT EXISTS temperatureHistory
                         (
                             id               serial primary key,
                             temperature      decimal     not null,
                             create_date_time timestamp,
                             city_id int ,
                             FOREIGN KEY (city_id) REFERENCES cityHistory (id)
                         );

                    """;
            statement.execute(createTemperatureHistory);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
