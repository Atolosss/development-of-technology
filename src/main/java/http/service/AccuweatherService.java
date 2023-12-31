package http.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import http.client.AccuweatherClient;
import http.mapper.AccuweatherMapper;
import http.model.dto.currentconditions.CurrentConditionsRoot;
import http.model.dto.currentconditions.CurrentConditionsRootWrapper;
import http.model.dto.topcities.TopcitiesRoot;
import http.model.entity.CityHistory;
import http.model.entity.TemperatureHistory;
import http.model.enums.CityNumber;
import http.repository.CityHistoryRepository;
import http.repository.TemperatureHistoryRepository;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
public class AccuweatherService {
    private final AccuweatherClient accuweatherClient;
    private final AccuweatherMapper accuweatherMapper;
    private final TemperatureHistoryRepository temperatureHistoryRepository;
    private final CityHistoryRepository cityHistoryRepository;

    private final ObjectMapper objectMapper;

    private final Map<CityNumber, TopcitiesRoot[]> topcitiesCache = new HashMap<>();


    private static void printCityNumbers() {
        System.out.println("Выбери количество городов: ");
        Arrays.stream(CityNumber.values())
                .forEach(cityNumber -> System.out.println(cityNumber.getValue()));
    }

    public void run() throws JsonProcessingException {
        try (var scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            String input;
            do {
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
                CurrentConditionsRootWrapper currentConditionsRoots = accuweatherClient.getCurrentConditions(String.valueOf(topcitiesRoot1.getKey()));
                CityHistory cityHistory = accuweatherMapper.toCityHistory(chosenCity);
                CityHistory city = findCity(cityHistory);


                // todo: сохранять в базу в новой колонке jsonb сырой ответ в формате json
                TemperatureHistory savedTemperatureHistory = temperatureHistoryRepository.save(
                        accuweatherMapper.toTemperatureHistory(currentConditionsRoots.getCurrentConditionsRoots(), city, currentConditionsRoots.getJson()));

                System.out.println(savedTemperatureHistory);
                System.out.println("Еще? 'Y' - да, любая другая клавиша - нет");
                input = scanner.next();
            } while ("Y".equals(input));
        }
    }

    public CityHistory findCity(CityHistory cityHistory) {
        Optional<CityHistory> city = cityHistoryRepository.findByName(cityHistory.getName());
        if (city.isEmpty()) {
            return cityHistoryRepository.save(cityHistory);
        }
        return city.get();
    }
}


