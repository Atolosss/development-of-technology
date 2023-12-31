package http.model.entity;

import http.model.dto.currentconditions.CurrentConditionsRoot;
import http.model.dto.jsonb.MyJsonb;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "temperatureHistory")
public class TemperatureHistory extends BaseEntity {

    @Column(name = "temperature")
    private BigDecimal temperature;

    @Column(name = "create_date_time", updatable = false, nullable = false)
    private LocalDateTime createDateTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @ToString.Exclude
    private CityHistory cityHistory;

    @Column(name = "rq_json")
    @JdbcTypeCode(SqlTypes.JSON)
    private String currentConditionsRoot;

}
