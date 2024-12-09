package capaNegocio;

import java.util.ArrayList;
import java.util.Arrays;

public class Productos {
	
	private int id;
	private String nombre;
	private ArrayList<PiezasConstruccion> piezasConstruccion;
	
	public Productos() {}
	
	public Productos(int id, String nombre, ArrayList<PiezasConstruccion> piezasConstruccion) {
		this.id = id;
		this.nombre = nombre;
		this.piezasConstruccion = piezasConstruccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<PiezasConstruccion> getPiezasConstruccion() {
		return piezasConstruccion;
	}

	public void setPiezasConstruccion(ArrayList<PiezasConstruccion> piezas) {
		this.piezasConstruccion = piezas;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto id=")
				.append(id)
				.append(", nombre=")
				.append(nombre)
				.append(", piezasConstruccion=")
				.append(piezasConstruccion);
				
		return builder.toString();
	}

	
	
}