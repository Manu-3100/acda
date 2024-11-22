package main;

import capaNegocio.*;

public class Main {

	public static void main(String[] args) {
		
		LogAnalyzer logAnalyzer = LogAnalyzerFactory.getAnalizador("D:\\ferdebman\\acda\\EjerciciosFactoryMethod\\src\\target\\errorLog.log");
			logAnalyzer.analiza().forEach(System.out::println);
	}	
}