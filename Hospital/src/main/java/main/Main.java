package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import capaNegocio.Doente;
import capaNegocio.Doente.ESTADO;

public class Main {

	public static void main(String[] args) {
		
		Doente.ESTADO estado = Doente.hacerIngreso2(2, LocalDate.now(), 111);
		
		if (estado == ESTADO.NOEXISTEPACIENTE)
			System.out.println("No existe el paciente");
		else if (estado == ESTADO.NOESTAINGRESADO)
			System.out.println("No esta ingresado");
		else if (estado == ESTADO.NOEXISTEHABITACION)
			System.out.println("No existe la habitación");
		else if (estado == ESTADO.NOHAYSITIOHABITACION)
			System.out.println("No hay sitio en la habitación");
		else if (estado == ESTADO.ERRORBD)
			System.out.println("Error en la base de datos");
		else
			System.out.println("Paciente ingresado correctamente");
	}

}
