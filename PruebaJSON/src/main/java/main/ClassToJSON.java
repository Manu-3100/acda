package main;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import capaNegocio.Car;

public class ClassToJSON {

	public static void main(String[] args) {

		ObjectMapper objectMapper = new ObjectMapper();
		
		Car car = new Car("red", "Skoda");
		
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			objectMapper.writeValue(new File("target/car.json"), car);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}

}
