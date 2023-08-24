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

//        System.out.println("Сколько стран?");
//        var scanner = new Scanner(System.in, StandardCharsets.UTF_8);
//        int anInt = scanner.nextInt();
//
//        var url = HttpUrl.parse(HOST)
//                .newBuilder()
//                .addPathSegment("locations")
//                .addPathSegment("v1")
//                .addPathSegment("topcities")
//                .addPathSegment(String.valueOf(anInt))
//                .addQueryParameter("apikey", "kRK8UAPHaOphrB82Ida62hMIdFnt36yB")
//                .build()
//                .toString();
//
//        var request = new Request.Builder()
//                .get()
//                .url(url)
//                .build();
//
//        System.out.println("Sending rq: " + request);
//        try (Response response = okHttpClient.newCall(request).execute()) {
//            System.out.println("Received rs: " + response);
//
//            String json = response.body().string();
//            System.out.println(json);
//
//
//            TopcitiesRoot[] topcitiesRoot = objectMapper.readValue(json, TopcitiesRoot[].class);
//            System.out.println(Arrays.toString(topcitiesRoot));
//
//            url = HttpUrl.parse(HOST)
//                    .newBuilder()
//                    .addPathSegment("currentconditions")
//                    .addPathSegment("v1")
//                    .addPathSegment(topcitiesRoot[0].getKey())
//                    .addQueryParameter("apikey", "kRK8UAPHaOphrB82Ida62hMIdFnt36yB")
//                    .build()
//                    .toString();
//
//            request = new Request.Builder()
//                    .get()
//                    .url(url)
//                    .build();
//
//            System.out.println("Sending rq: " + request);
//            try (Response response1 = okHttpClient.newCall(request).execute()) {
//                System.out.println("Received rs: " + response1);
//
//                json = response1.body().string();
//                System.out.println(json);
//
//
//                CurrentConditionsRoot[] currentConditionsRoots =
//                        objectMapper.readValue(json, CurrentConditionsRoot[].class);
//                System.out.println(Arrays.toString(currentConditionsRoots));
//            }
//        }

    }
}
