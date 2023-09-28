package http.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cityHistory")
public class CityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "cityHistory",
            fetch = FetchType.LAZY)
    private List<TemperatureHistory> temperatureHistories;

    @Override
    public String toString() {
        return "CityHistory{" +
                "id=" + id +
                ", city='" + city + '\'' +
//                ", temperatureHistories=" + temperatureHistories +
                '}';
    }
}
