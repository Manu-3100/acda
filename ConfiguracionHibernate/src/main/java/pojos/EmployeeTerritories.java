package pojos;

import jakarta.persistence.*;

public class EmployeeTerritories {
	@Entity
	@Table(name = "EmployeeTerritories")
	public class EmployeeTerritory {
	    @EmbeddedId
	    private EmployeeTerritoryId id;
	    
	    @ManyToOne
	    @MapsId("employeeID")
	    @JoinColumn(name = "EmployeeID")
	    private Employee employee;
	    
	    @ManyToOne
	    @MapsId("territoryID")
	    @JoinColumn(name = "TerritoryID")
	    private Territory territory;

	    // Getters and Setters
		public EmployeeTerritoryId getId() {
			return id;
		}

		public void setId(EmployeeTerritoryId id) {
			this.id = id;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public Territory getTerritory() {
			return territory;
		}

		public void setTerritory(Territory territory) {
			this.territory = territory;
		}

		@Override
		public String toString() {
			return "EmployeeTerritory [id=" + id + ", employee=" + employee + ", territory=" + territory + "]";
		}
	}
}