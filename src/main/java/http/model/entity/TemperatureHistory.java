package http.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "temperatureHistory")
public class TemperatureHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private BigDecimal temperature;

    @Column(name = "create_date_time", updatable = false, nullable = false)
    private LocalDateTime createDateTime;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @ToString.Exclude
    private CityHistory cityHistory;


}
