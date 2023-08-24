package http.service;

import http.client.AccuweatherClient;
import http.model.dto.topcities.TopcitiesRoot;
import http.model.enums.CityNumber;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

@RequiredArgsConstructor
public class AccuweatherService {
    private final AccuweatherClient accuweatherClient;

    private static void printCityNumbers() {
        Arrays.stream(CityNumber.values())
                .forEach(cityNumber -> System.out.println(cityNumber.getValue()));
    }

    //TODO: доделать вызов и вывод прогноза погоды
    //TODO: необходимо выбирать город в котором нужно получить погоду по наименованию
    public void run() {
        //TODO: close resource
        var scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        String input;
        do {
            System.out.println("Выбери количество городов: ");
            printCityNumbers();

            var cityNumber = CityNumber.getByValue(scanner.nextInt());
            TopcitiesRoot[] topcities = accuweatherClient.getTopcities(cityNumber);


            System.out.println(Arrays.toString(topcities));

            System.out.println("Еще? 'Y' - да, любая другая клавиша - нет");
            input = scanner.next();
        } while ("Y".equals(input));
    }
}
