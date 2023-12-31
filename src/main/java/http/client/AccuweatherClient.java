package http.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import http.model.dto.currentconditions.CurrentConditionsRoot;
import http.model.dto.currentconditions.CurrentConditionsRootWrapper;
import http.model.dto.topcities.TopcitiesRoot;
import http.model.enums.CityNumber;
import http.utils.ReadPropertiesUtils;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


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
                .addQueryParameter("apikey", ReadPropertiesUtils.getProperty("apikey"))
                .build()
                .toString();

        TypeReference<TopcitiesRoot[]> typeReference = new TypeReference<>() {
        };
        return call(url, typeReference);
    }

    public CurrentConditionsRootWrapper getCurrentConditions(final String key) {
        var url = HttpUrl.parse(HOST)
                .newBuilder()
                .addPathSegment("currentconditions")
                .addPathSegment("v1")
                .addPathSegment(key)
                .addQueryParameter("apikey", ReadPropertiesUtils.getProperty("apikey"))
                .build()
                .toString();

        return call(url);

    }

    private <T> T call(final String url, final TypeReference<T> typeReference) {
        var request = new Request.Builder()
                .get()
                .url(url)
                .build();
        System.out.println("Sending rq: " + request);
        try (Response response = okHttpClient.newCall(request).execute()) {
            System.out.println("Reciveng rs: " + response);
            String json = response.body().string();
            System.out.println(json);

            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CurrentConditionsRootWrapper call(final String url) {
        var request = new Request.Builder()
                .get()
                .url(url)
                .build();
        System.out.println("Sending rq: " + request);
        try (Response response = okHttpClient.newCall(request).execute()) {
            System.out.println("Reciveng rs: " + response);
            String json = response.body().string();
            System.out.println(json);

            CurrentConditionsRoot[] currentConditionsRoots = objectMapper.readValue(json, CurrentConditionsRoot[].class);
            return CurrentConditionsRootWrapper.builder()
                    .currentConditionsRoots(currentConditionsRoots)
                    .json(json)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




