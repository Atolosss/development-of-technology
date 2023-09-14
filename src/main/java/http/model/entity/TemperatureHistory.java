package http.model.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TemperatureHistory {
    private Long id;
    private String city;
    private BigDecimal temperature;
    private LocalDateTime createDateTime;
}
