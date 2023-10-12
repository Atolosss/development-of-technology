package http.model.dto.currentconditions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentConditionsRootWrapper {
    private CurrentConditionsRoot[] currentConditionsRoots;

    private String json;
}
