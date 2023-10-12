package http.model.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cityHistory")
public class CityHistory extends BaseEntity {

    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "cityHistory",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @Builder.Default
    private List<TemperatureHistory> temperatureHistories = new ArrayList<>();

}
