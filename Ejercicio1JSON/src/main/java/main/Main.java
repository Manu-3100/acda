package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import capaNegocio.*;

public class Main {

	public static void main(String[] args) {	
			apartadoB();
	}	
	
	private static void apartadoA () {
		try {
			byte[] jsonData = Files.readAllBytes(Paths.get("target/empleado.json"));
			ObjectMapper objectMapper = new ObjectMapper();		
	
			Empleado empleado = objectMapper.readValue(jsonData, Empleado.class);
			
			System.out.println(empleado);
	
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static void apartadoB () {
		try {
			ObjectMapper objectMapper = new ObjectMapper();		
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.writeValue(new File("target/nuevoEmpleado.json"), Empleado.create());
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}	
	}
}
