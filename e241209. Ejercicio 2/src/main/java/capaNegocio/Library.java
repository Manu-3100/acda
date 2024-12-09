package capaNegocio;

public class Library {
	private String nombre;
	private String version;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Library [nombre=" + nombre + ", version=" + version + "]";
	}
}