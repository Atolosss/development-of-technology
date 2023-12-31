package http.mapper;

import http.model.dto.currentconditions.CurrentConditionsRoot;
import http.model.entity.CityHistory;
import http.model.entity.TemperatureHistory;
import java.time.LocalDateTime;

public class AccuweatherMapper {
    public TemperatureHistory toTemperatureHistory(final CurrentConditionsRoot[] currentConditionsRoots, final CityHistory cityHistory,final String json) {
        return TemperatureHistory.builder()
                .temperature(currentConditionsRoots[0].getTemperature().getMetric().getValue())
                .createDateTime(LocalDateTime.now())
                .cityHistory(cityHistory)
                .currentConditionsRoot(json)
                .build();
    }
    public CityHistory toCityHistory(final String name) {
        return CityHistory.builder()
                .name(name)
                .build();
    }
}
