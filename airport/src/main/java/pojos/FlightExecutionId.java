package pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class FlightExecutionId implements Serializable {
    @Column(name = "FlightNo", length = 7)
    private String flightNo;

    @Column(name = "DeparTime")
    private java.sql.Timestamp deparTime;
    
	public FlightExecutionId(String flightNo, Timestamp deparTime) {
		this.flightNo = flightNo;
		this.deparTime = deparTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public java.sql.Timestamp getDeparTime() {
		return deparTime;
	}

	public void setDeparTime(java.sql.Timestamp deparTime) {
		this.deparTime = deparTime;
	}
    
    
}
