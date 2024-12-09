package capaNegocio;

public class PiezasConstruccion {

	private String material;
	private int cantidad;
	private String unidad;
	
	public PiezasConstruccion() {}
	
	public PiezasConstruccion(String material, int cantidad, String unidad) {
		this.material = material;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PiezaConstruccion material=")
			   .append(material)
			   .append(", cantidad=")
			   .append(cantidad)
			   .append(", unidad=");
		return builder.toString();
	}
	
	
	
	
}
