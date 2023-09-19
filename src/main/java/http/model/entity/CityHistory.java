package http.model.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CityHistory {
    private int idCity;
    private  String city;
    private BigDecimal temperature;
}
