package http.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
//@NamedEntityGraph(name = "cityHistory_entity-graph",attributeNodes = @NamedAttributeNode("temperatureHistories"))
@Table(name = "cityHistory")
public class CityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityHistory",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<TemperatureHistory> temperatureHistories;


}
