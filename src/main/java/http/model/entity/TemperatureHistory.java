package http.model.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class TemperatureHistory extends BaseEntity {
    private BigDecimal temperature;
    private LocalDateTime createDateTime;
    private Long cityId;

}
