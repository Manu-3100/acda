package pojos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class FlightExecutionId implements Serializable {
    @Column(name = "FlightNo", length = 7)
    private String flightNo;

    @Column(name = "DeparTime")
    private java.sql.Timestamp deparTime;
}
