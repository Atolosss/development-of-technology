package http;

import com.fasterxml.jackson.databind.ObjectMapper;
import http.client.AccuweatherClient;
import http.mapper.AccuweatherMapper;
import http.model.entity.TemperatureHistory;
import http.repository.CityHistoryRepository;
import http.repository.TemperatureHistoryRepository;
import http.service.AccuweatherService;
import http.utils.HibernateUtil;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

import java.util.List;

import static http.utils.HibernateUtil.getSessionFactory;

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

//        CityHistory cityHistory = null;
//        try (var session = getSessionFactory().openSession()) {
//            cityHistory = session.get(CityHistory.class, 1);
//            System.out.println(cityHistory.toString());
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//        System.out.println(cityHistory.toString());
//    }
        try (var session = getSessionFactory().openSession()) {
            SelectionQuery<TemperatureHistory> query = session.createQuery("FROM TemperatureHistory temp where temp.cityHistory.id=1 ", TemperatureHistory.class);
            List<TemperatureHistory> resultList = query.getResultList();
            for (TemperatureHistory temperatureHistory: resultList) {
                System.out.println(temperatureHistory.getCityHistory());
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
