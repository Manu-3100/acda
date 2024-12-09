package capaNegocio;

import java.util.ArrayList;

public class Inventario {

	private ArrayList<Productos> productos;
	private ArrayList<Materiales> materiales;
	
	
	public Inventario() {}
	
	public Inventario(ArrayList<Productos> productos, ArrayList<Materiales> materiales) {
		this.productos = productos;
		this.materiales = materiales;
	}
	
	public ArrayList<Productos> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Productos> productos) {
		this.productos = productos;
	}
	public ArrayList<Materiales> getMateriales() {
		return materiales;
	}
	public void setMateriales(ArrayList<Materiales> materiales) {
		this.materiales = materiales;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inventario productos=")
		.append(productos)
		.append(", materiales=")
		.append(materiales);
		return builder.toString();
	}
	
	
	
	
}
