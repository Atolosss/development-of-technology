package http.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "temperatureHistory")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal temperature;
    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;
    @Column(name = "city_id")
    private Long cityId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private CityHistory cityHistory;
}
