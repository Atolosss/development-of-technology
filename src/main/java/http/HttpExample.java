package http;

import com.fasterxml.jackson.databind.ObjectMapper;
import http.client.AccuweatherClient;
import http.service.AccuweatherService;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;

public class HttpExample {

    @SneakyThrows
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        AccuweatherClient accuweatherClient = new AccuweatherClient(okHttpClient, objectMapper);
        AccuweatherService accuweatherService = new AccuweatherService(accuweatherClient);
        accuweatherService.run();

    }
}