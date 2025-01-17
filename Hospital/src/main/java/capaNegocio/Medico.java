package capaNegocio;

public class Medico {
	
	private int id;

	public Medico(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Medico [id=");
		builder.append(id);
		return builder.toString();
	}
	
	
	
	
	
	
	
	
}
