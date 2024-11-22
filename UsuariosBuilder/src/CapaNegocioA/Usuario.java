package CapaNegocioA;

public class Usuario {

	private String email;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String direccion;
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("nombre = ").append(nombre).append(" , email = ").append(email).append(", apellidos = ")
				.append(apellidos).append(", telefono = ").append(telefono).append(", direccion = ").append(direccion);
		return sb.toString();
	}
}