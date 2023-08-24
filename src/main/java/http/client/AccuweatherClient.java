package http.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import http.model.dto.topcities.TopcitiesRoot;
import http.model.enums.CityNumber;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

//TODO: current conditions method
//TODO: Вынести общую часть из обоих методов отдельный приватный метод (дженерики)

@RequiredArgsConstructor
public class AccuweatherClient {

    private static final String HOST = "https://dataservice.accuweather.com";

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public TopcitiesRoot[] getTopcities(final CityNumber cityNumber) {
        var url = HttpUrl.parse(HOST)
                .newBuilder()
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("topcities")
                .addPathSegment(String.valueOf(cityNumber.getValue()))
                //TODO: Сделать класс ReadPropertiesUtils в котором поместить apikey и читать оттуда
                .addQueryParameter("apikey", "")
                .build()
                .toString();

        var request = new Request.Builder()
                .get()
                .url(url)
                .build();

        System.out.println("Sending rq: " + request);
        try (Response response = okHttpClient.newCall(request).execute()) {
            System.out.println("Received rs: " + response);

            String json = response.body().string();
            System.out.println(json);

            return objectMapper.readValue(json, TopcitiesRoot[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
