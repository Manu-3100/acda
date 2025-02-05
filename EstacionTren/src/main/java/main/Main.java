package main;

import java.io.File;
import capaNegocio.Trenes;

public class Main {
	
	public static void main(String[] args) {
		
		if(!Trenes.existeBD()) {
			// crear la base de datos si no existe
			Trenes.crearBaseDatos("D:\\ferdebman\\2DAM\\acda\\basesDatos\\estacionTren\\SQLparaLaCreacionDelEsquema.sql");	
			// insertar datos
			Trenes.insertarDatos("D:\\ferdebman\\2DAM\\acda\\basesDatos\\estacionTren\\CargaDatosBase.sql");
		} else {
			System.err.println("Ya esta creada, conectando");
		}
		
		
		
		
		
	}
	
	
	
	
	
}
