package ej3;

import java.time.LocalDateTime;

public class Noticia {
	
	private String titulo;
	private String autor;
	private String categoria;
	private LocalDateTime pubFecha;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public LocalDateTime getPubFecha() {
		return pubFecha;
	}
	public void setPubFecha(LocalDateTime pubFecha) {
		this.pubFecha = pubFecha;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n").append("Titulo: ").append(titulo).append(", autor: ").append(autor).append(", categoria: ")
				.append(categoria).append(", pubFecha: ").append(pubFecha);
		return builder.toString();
	}
	
	

}
