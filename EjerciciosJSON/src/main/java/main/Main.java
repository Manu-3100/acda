package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import capaNegocio.*;

public class Main {
	
	private static void apartadoA() {
		try {
			byte[] jsonData = Files.readAllBytes(Paths.get("target/empleado.json"));
			ObjectMapper objectMapper = new ObjectMapper();
			Empleado empleado = objectMapper.readValue(jsonData, Empleado.class);
			System.out.println(empleado);
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	private static void apartadoB() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.writeValue(new File("target/nuevoEmpleado.json"), Empleado.create());
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	private static List<Empleado> apartadoD() {
		ArrayList<Empleado> empleados = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			byte[] jsonData = Files.readAllBytes(Paths.get("target/empleados.json"));
			
			empleados = objectMapper.readValue(jsonData, new TypeReference<ArrayList<Empleado>>() {});
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return empleados;
	}
	
	private static Empleado[] apartadoD2() {
		Empleado[] empleados = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			byte[] jsonData = Files.readAllBytes(Paths.get("target/empleados.json"));
			
			empleados = objectMapper.readValue(jsonData, Empleado[].class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return empleados;
	}
	
	private static JsonNode leerEmpleado() {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode nodo = null;
		try {
			nodo = objectMapper.readTree(new File("target/empleado.json"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return nodo;
	}
	
	private static void addApellidos(JsonNode raiz, String apellidos) {
		ObjectNode nodo = (ObjectNode) raiz;
		nodo.put("surname", apellidos);
		
		// Prueba para ver si funciona correctamente.
//		String json;
//		try {
//			json = new ObjectMapper()
//					.writerWithDefaultPrettyPrinter()
//					.writeValueAsString(nodo);
//			System.out.println(json);
//		} catch (JsonProcessingException e) {
//			System.out.println(e.getMessage());
//		}
	}
	
	private static void deletePropiedades(JsonNode raiz) {
		if (raiz.has("properties"))
			((ObjectNode)raiz).remove("properties");
	}
	
	private static int save(JsonNode nodo, String fileName) {
		int res = 0;
		try {
			new ObjectMapper()
				.writerWithDefaultPrettyPrinter()
				.writeValue(new File(fileName), nodo);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			res = -1;
		}
		return res;
	}
	
	private static void mostrar(String fileName) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			JsonNode jsonNode = objectMapper.readTree(new File(fileName));
			System.out.println(
				objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(jsonNode)
			);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
//		Este código no funciona porque el objeto Empleado carece de la propiedad "surname"
//		try {
//			Empleado empleado = objectMapper.readValue(new File(fileName), Empleado.class);
//			System.out.println(empleado);
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
	}
	
	public static void main(String[] args) {
		JsonNode raiz = leerEmpleado();
		addApellidos(raiz, "López Fernández");
		deletePropiedades(raiz);
		if (save(raiz, "target/apartadoE.json") == 0)
			mostrar("target/apartadoE.json");
		else
			System.out.println("Error al generar el JSON.");
	}
}
