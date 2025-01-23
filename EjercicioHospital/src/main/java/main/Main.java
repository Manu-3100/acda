package main;

import java.time.LocalDate;

import capaNegocio.*;

public class Main {

	public static void main(String[] args) {
		Paciente.ESTADO estado = Paciente.hacerIngreso2(467, LocalDate.now(), 353);
		if (estado == Paciente.ESTADO.NOEXISTEPACIENTE)
			System.out.println("No existe el paciente");
		else if (estado == Paciente.ESTADO.NOEXISTEHABITACION)
			System.out.println("No existe la habitación");
		else if (estado == Paciente.ESTADO.NOHAYSITIOHABITACION)
			System.out.println("No hay sitio en la habitación");
		else if(estado == Paciente.ESTADO.PACIENTEYAINGRESADO)
			System.out.println("El paciente ya está ingresado");
		else if (estado == Paciente.ESTADO.ERRORBD)
			System.out.println("Error en la base de datos");
		else
			System.out.println("Paciente ingresado correctamente");
	}

}
