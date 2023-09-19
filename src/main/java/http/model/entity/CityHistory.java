package http.model.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CityHistory extends BaseEntity {
    private String city;
}
