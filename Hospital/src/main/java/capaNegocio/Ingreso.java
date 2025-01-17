package capaNegocio;

import java.time.LocalDate;

public class Ingreso {

	private int ing_id;
	private int ing_nhdoente;
	private LocalDate ing_dataingreso;
	private int ing_numhabitacion;
	private String ing_dataalta;
	
	public Ingreso(int ing_id, int ing_nhdoente, LocalDate ing_dataingreso, int ing_numhabitacion,
			String ing_dataalta) {
		this.ing_id = ing_id;
		this.ing_nhdoente = ing_nhdoente;
		this.ing_dataingreso = ing_dataingreso;
		this.ing_numhabitacion = ing_numhabitacion;
		this.ing_dataalta = ing_dataalta;
	}
	
	public int getIng_id() {
		return ing_id;
	}
	
	public void setIng_id(int ing_id) {
		this.ing_id = ing_id;
	}
	
	public int getIng_nhdoente() {
		return ing_nhdoente;
	}
	
	public void setIng_nhdoente(int ing_nhdoente) {
		this.ing_nhdoente = ing_nhdoente;
	}
	
	public LocalDate getIng_dataingreso() {
		return ing_dataingreso;
	}
	
	public void setIng_dataingreso(LocalDate ing_dataingreso) {
		this.ing_dataingreso = ing_dataingreso;
	}
	
	public int getIng_numhabitacion() {
		return ing_numhabitacion;
	}
	
	public void setIng_numhabitacion(int ing_numhabitacion) {
		this.ing_numhabitacion = ing_numhabitacion;
	}
	
	public String getIng_dataalta() {
		return ing_dataalta;
	}
	
	public void setIng_dataalta(String ing_dataalta) {
		this.ing_dataalta = ing_dataalta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ingreso [ing_id=");
		builder.append(ing_id);
		builder.append(", ing_nhdoente=");
		builder.append(ing_nhdoente);
		builder.append(", ing_dataingreso=");
		builder.append(ing_dataingreso);
		builder.append(", ing_numhabitacion=");
		builder.append(ing_numhabitacion);
		builder.append(", ing_dataalta=");
		builder.append(ing_dataalta);
		return builder.toString();
	}
	
	
	
	
	

}
