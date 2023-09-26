package http;

import com.fasterxml.jackson.databind.ObjectMapper;
import http.client.AccuweatherClient;
import http.mapper.AccuweatherMapper;
import http.repository.CityHistoryRepository;
import http.repository.TemperatureHistoryRepository;
import http.service.AccuweatherService;
import http.utils.HibernateUtil;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import org.hibernate.SessionFactory;

public class App {

    @SneakyThrows
    public static void main(final String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        AccuweatherClient accuweatherClient = new AccuweatherClient(okHttpClient, objectMapper);
        TemperatureHistoryRepository temperatureHistoryRepository = new TemperatureHistoryRepository();
        CityHistoryRepository cityHistoryRepository = new CityHistoryRepository();
        AccuweatherService accuweatherService = new AccuweatherService(
                accuweatherClient, new AccuweatherMapper(), temperatureHistoryRepository, cityHistoryRepository);

        accuweatherService.run();

    }
}
