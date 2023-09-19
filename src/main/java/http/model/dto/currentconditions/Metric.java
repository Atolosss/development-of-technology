package http.model.dto.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Metric {
    @JsonProperty("Value")
    private BigDecimal value;
    @JsonProperty("Unit")
    private String unit;
    @JsonProperty("UnitType")
    private int unitType;
}
