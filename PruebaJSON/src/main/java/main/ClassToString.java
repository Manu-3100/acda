package main;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import capaNegocio.Car;

public class ClassToString {

	public static void main(String[] args) {

		ObjectMapper objectMapper = new ObjectMapper();
		
		Car car = new Car("red", "Skoda");
		
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		StringWriter stringCar = new StringWriter();
		
		try {
			objectMapper.writeValue(stringCar, car);
			System.out.println(stringCar.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
//		try {
//			String carAsString = objectMapper.writeValueAsString(car);
//			System.out.println(carAsString);
//		} catch (JsonProcessingException e) {
//			System.err.println(e.getMessage());
//		}
		
	}

}
