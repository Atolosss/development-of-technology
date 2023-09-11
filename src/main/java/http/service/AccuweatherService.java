package http.service;

import http.client.AccuweatherClient;
import http.model.dto.currentconditions.CurrentConditionsRoot;
import http.model.dto.topcities.TopcitiesRoot;
import http.model.enums.CityNumber;
import http.repository.CrudRepository;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class AccuweatherService {
    private final AccuweatherClient accuweatherClient;

    private final Map<CityNumber, TopcitiesRoot[]> topcitiesCache = new HashMap<>();


    private final CrudRepository CrudRepository = new CrudRepository();

    private static void printCityNumbers() {
        Arrays.stream(CityNumber.values())
                .forEach(cityNumber -> System.out.println(cityNumber.getValue()));
    }

    public void run() {
        try (var scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            String input;
            do {
                System.out.println("Выбери количество городов: ");
                printCityNumbers();

                var cityNumber = CityNumber.getByValue(scanner.nextInt());
                if (!topcitiesCache.containsKey(cityNumber)) {
                    topcitiesCache.put(cityNumber, accuweatherClient.getTopcities(cityNumber));
                }

                System.out.println("Выбирете город : ");
                Arrays.stream(topcitiesCache.get(cityNumber))
                        .forEach(System.out::println);
                String chosenCity = scanner.next();


                TopcitiesRoot topcitiesRoot1 = Arrays.stream(topcitiesCache.get(cityNumber))
                        .filter(topcitiesRoot -> topcitiesRoot.getEnglishName().equals(chosenCity))
                        .findFirst()
                        .orElseThrow();
                CurrentConditionsRoot[] currentConditionsRoots = accuweatherClient.getCurrentConditions(String.valueOf(topcitiesRoot1.getKey()));
                Arrays.stream(currentConditionsRoots)
                        .forEach(System.out::println);
//                CrudRepository.DatabaseConnection();
//                CrudRepository.createRecord(topcitiesRoot1, Arrays.stream(currentConditionsRoots).findFirst(temp) );
//                CrudRepository.closeConnection();
                System.out.println("Еще? 'Y' - да, любая другая клавиша - нет");
                input = scanner.next();
            } while ("Y".equals(input));
        }

    }

}


