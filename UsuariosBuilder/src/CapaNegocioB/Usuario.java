package CapaNegocioB;

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
	
	public static class Builder{
		
		private Usuario usuario = new Usuario();
		
		public Builder nombre (String nombre) {
			usuario.setNombre(nombre);	
			return this;
		}
		
		public Builder apellidos (String apellidos) {
			usuario.setApellidos(apellidos);	
			return this;
		}
		
		public Builder email (String email) {
			usuario.setEmail(email);	
			return this;
		}
		
		public Builder telefono (String telefono) {
			usuario.setTelefono(telefono);	
			return this;
		}
		
		public Builder direccion (String direccion) {
			usuario.setDireccion(direccion);	
			return this;
		}
		
		public Usuario build() {
			return usuario;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("nombre = ").append(nombre).append(" , email = ").append(email).append(", apellidos = ")
				.append(apellidos).append(", telefono = ").append(telefono).append(", direccion = ").append(direccion);
		return sb.toString();
	}
	

}
