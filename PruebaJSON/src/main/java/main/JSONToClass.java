package main;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import capaNegocio.Car;

public class JSONToClass {

	public static void main(String[] args) {

		ObjectMapper objectMapper = new ObjectMapper();
		
		
		try {
			byte[] jsonData = Files.readAllBytes(Paths.get("target/car.json"));
			ArrayList <Car> cars = objectMapper.readValue(jsonData, new TypeReference<ArrayList<Car>>() {});
			cars.forEach(System.out::println);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		
//		try {
//			Car car = objectMapper.readValue(new File("target/car.json"), Car.class);
//			System.out.println(car);
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
	}
}