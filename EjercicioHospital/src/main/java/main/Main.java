package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import capaNegocio.*;

public class Main {

	public static void main(String[] args) throws SQLException {
//		Paciente.ESTADO estado = Paciente.hacerIngreso2(467, LocalDate.now(), 353);
//		if (estado == Paciente.ESTADO.NOEXISTEPACIENTE)
//			System.out.println("No existe el paciente");
//		else if (estado == Paciente.ESTADO.NOEXISTEHABITACION)
//			System.out.println("No existe la habitación");
//		else if (estado == Paciente.ESTADO.NOHAYSITIOHABITACION)
//			System.out.println("No hay sitio en la habitación");
//		else if(estado == Paciente.ESTADO.PACIENTEYAINGRESADO)
//			System.out.println("El paciente ya está ingresado");
//		else if (estado == Paciente.ESTADO.ERRORBD)
//			System.out.println("Error en la base de datos");
//		else
//			System.out.println("Paciente ingresado correctamente");
		
		// Medico.estadisticasMedico(1).forEach(System.out::println);
		
		if(Paciente.borrar(2))
			System.out.println("Paciente Borrado");
		else
			System.out.println("Error en el borrado");

		
	}

}
