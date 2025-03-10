package Streams;

public class Alumno {
	private int id;
	private String nombre;
	private String apellidos;
	private String nombreCurso;
	private double nota;
	private int edad;
	
	public Alumno() {
		
	}
	
	public Alumno(int id, String nombres, String apellidos, String nombreCurso, double nota, int edad) {
		this.id = id;
		this.nombre = nombres;
		this.apellidos = apellidos;
		this.nombreCurso = nombreCurso;
		this.nota = nota;
		this.edad = edad;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return nombre;
	}

	public void setNombres(String nombres) {
		this.nombre = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return id+" | "+nombre+" | "+apellidos+" | Curso: "+nombreCurso+" | Nota: "+nota+" | Edad: "+edad;
	}
}