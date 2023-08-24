package http.model.dto.topcities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopcitiesRoot {
    @JsonProperty("Key")
    private String key;
    @JsonProperty("EnglishName")
    private String englishName;
}
