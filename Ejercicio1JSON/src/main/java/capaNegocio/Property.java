package capaNegocio;

public class Property {

	private String age;
	private String salary;
	
	public Property(String age, String salary) {
		this.age = age;
		this.salary = salary;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Properties => age: ").append(age).append(", salary: ").append(salary);
		return builder.toString();
	}
	
	
}
