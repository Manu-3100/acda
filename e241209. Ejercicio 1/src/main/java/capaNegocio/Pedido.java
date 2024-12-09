package capaNegocio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido implements Comparable<Pedido>{
	private int idCliente;
	private int idProducto;
	private int cantidad;
	private LocalDate fecha;
	
	public Pedido(int idCliente, int idProducto, int cantidad, LocalDate fecha) {
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Pedido [idCliente=" + idCliente + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", fecha="
				+ fecha + "]";
	}

	@Override
	public int compareTo(Pedido o) {
		return this.fecha.compareTo(o.fecha);
	}
	
	/**
	 * Método de la clase Pedido. En un pedido figura un producto.
	 * Este método busca el objeto Producto solicitado a partir de su identificador.
	 * @param inventario
	 * @return Devuelve el objeto Producto que se solicita en el pedido.
	 */
	private Productos getProducto(Inventario inventario) {
		Productos res = null;
		for (Productos producto : inventario.getProductos()) {
			if(producto.getId() == idProducto) {
				res = producto;
			}
		}
		
		return res;
	}
	
	/**
	 * Método que a partir de un producto solicitado y de la lista de materiales de 
	 * los que se dispone, indica si hay suficiente stock.
	 * Importante darse cuenta que en la instancia de esta clase (Pedido) figura
	 * el número de productos solicitados.
	 * @param productos
	 * @param materiales
	 * @return Indicativo de si hay stock para fabricar las unidades indicadas en 
	 * el pedido del producto a partir del stock.
	 */
	private boolean hayMateriales(Productos productos, ArrayList<Materiales> materiales){
		
		for(PiezasConstruccion pieza : productos.getPiezasConstruccion()) {
			for(Materiales material : materiales) {
				if(material.getNombre().equals(pieza.getMaterial()) &&
					material.getStock()	> pieza.getCantidad()){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Una vez tramitado un pedido con éxito por tener stock suficiente, este método
	 * actualiza el stock de los materiales utilizados. 
	 * @param productos
	 * @param materiales
	 */
	private void actualizaMateriales(Productos productos, ArrayList<Materiales> materiales){
		
		for(PiezasConstruccion pieza : productos.getPiezasConstruccion()) {
			for(Materiales material : materiales) {
				if(material.getNombre().equals(pieza.getMaterial())){
					material.setStock(material.getStock() - pieza.getCantidad());
				}
			}
		}
		
		
	}

	// Ejercicio 1.c)		2 puntos
	// Los métodos que vienen a continuación pueden ser útiles para tramitar un pedido, pero 
	// no tienen por qué utilizarse. Se admite cualquier otra codificación siempre y cuando
	// resuelva el problema y los métodos tengan una cohesión alta.
	
	/**
	 * Tramitación de un pedido a partir del inventario (descripción de productos que se
	 * fabrican más lista de materiales disponibles).
	 * @param inventario
	 * @return indicativo de si se pudo tramitar o no el pedido.
	 */
	public boolean tramitar(Inventario inventario) {
		
		Productos producto = getProducto(inventario);
		
		if(hayMateriales(producto, inventario.getMateriales())) {
			actualizaMateriales(producto, inventario.getMateriales());
			return true;
		}
		return false;
		
	}
}
