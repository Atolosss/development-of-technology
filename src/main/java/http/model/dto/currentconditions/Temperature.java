package http.model.dto.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Temperature {
    @JsonProperty("Metric")
    private Metric metric;
    @JsonProperty("Imperial")
    private Imperial imperial;
}
