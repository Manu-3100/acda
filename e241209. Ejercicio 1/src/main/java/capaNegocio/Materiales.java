package capaNegocio;

public class Materiales {

	private String nombre;
	private String unidad;
	private double costoPorUnidad;
	private int stock;
	
	public Materiales() {}
	
	public Materiales(String nombre, String unidad, double costoPorUnidad, int stock) {
		this.nombre = nombre;
		this.unidad = unidad;
		this.costoPorUnidad = costoPorUnidad;
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public double getCostoPorUnidad() {
		return costoPorUnidad;
	}

	public void setCostoPorUnidad(double costoPorUnidad) {
		this.costoPorUnidad = costoPorUnidad;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Materiales nombre=")
				.append(nombre)
				.append(", unidad=")
				.append(unidad)
				.append(", costoPorUnidad=")
				.append(costoPorUnidad).append(", stock=");
		return builder.toString();
	}
	
	
	
}
