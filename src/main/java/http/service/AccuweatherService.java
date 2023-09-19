package http.service;

import http.client.AccuweatherClient;
import http.model.dto.currentconditions.CurrentConditionsRoot;
import http.model.dto.topcities.TopcitiesRoot;
import http.model.entity.CityHistory;
import http.model.entity.TemperatureHistory;
import http.model.enums.CityNumber;
import http.repository.CityHistoryRepository;
import http.repository.TemperatureHistoryRepository;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class AccuweatherService {
    private final AccuweatherClient accuweatherClient;
    private final TemperatureHistoryRepository temperatureHistoryRepository;

    private final CityHistoryRepository cityHistoryRepository;

    private final Map<CityNumber, TopcitiesRoot[]> topcitiesCache = new HashMap<>();


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
                System.out.println();
                Arrays.stream(currentConditionsRoots)
                        .forEach(System.out::println);

                save(TemperatureHistory.builder()
                        .id(temperatureHistoryRepository.getMaxId())
                        .city(chosenCity)
                        .temperature(currentConditionsRoots[0].getTemperature().getMetric().getValue())
                        .createDateTime(LocalDateTime.now())
                        .idCity(cityHistoryRepository.findByCity(chosenCity))
                        .build());
                save(CityHistory.builder().
                        idCity(cityHistoryRepository.getMaxId())
                        .city(chosenCity)
                        .temperature(currentConditionsRoots[0].getTemperature().getMetric().getValue())
                        .build());

                System.out.println("Еще? 'Y' - да, любая другая клавиша - нет");
                input = scanner.next();
            } while ("Y".equals(input));
        }

    }

    public TemperatureHistory findById(Long id) {
        return temperatureHistoryRepository.findById(id).orElseThrow();
    }

    public List<TemperatureHistory> findAll() {
        return temperatureHistoryRepository.findAll();
    }

    public void save(TemperatureHistory temperatureHistory) {
        temperatureHistoryRepository.save(temperatureHistory);
    }

    public void delete(final Long id) {
        temperatureHistoryRepository.delete(id);
    }

    public CityHistory findById(Integer id){return cityHistoryRepository.findById(id).orElseThrow();}

    public void save(CityHistory cityHistory){
        cityHistoryRepository.save(cityHistory);
    }



}


