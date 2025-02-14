package pojos;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class EmployeeTerritoryId implements Serializable {
    private Integer employeeID;
    private String territoryID;

    public EmployeeTerritoryId() {}

    public EmployeeTerritoryId(Integer employeeID, String territoryID) {
        this.employeeID = employeeID;
        this.territoryID = territoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTerritoryId that = (EmployeeTerritoryId) o;
        return Objects.equals(employeeID, that.employeeID) && Objects.equals(territoryID, that.territoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, territoryID);
    }
}