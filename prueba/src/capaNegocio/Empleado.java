package capaNegocio;

import java.text.NumberFormat;

public class Empleado {

	private int codigo;
	private String nombre;
	private double salario;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}

	
	public Empleado() {}
	
	public Empleado(int codigo, String nombre, double salario) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.salario = salario;
	}
	
	public void incrementarSalario(double porcentaje) {
		salario += salario * porcentaje;	
	}
	
	public String nombreMayusculas() {
		return nombre.toUpperCase();
	}

	public Empleado mayusculas() {
		Empleado e = new Empleado();
		e.codigo = this.codigo;
		e.nombre = this.nombre.toUpperCase();
		e.salario = this.salario;
		
		return this;
	}
	
	@Override
	public String toString() {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance() ;
		
		StringBuilder builder = new StringBuilder();
		builder.append("codigo= ")
				.append(codigo)
				.append(", nombre= ")
				.append(nombre)
				.append(", salario= ")
				.append(nf.format(salario));
		return builder.toString();
	}

	
	
	
	
	
	
}
