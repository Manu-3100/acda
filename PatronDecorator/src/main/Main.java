package main;

public class Main {
	
	public static void main(String[] args) {
		
		Enemigo enemigo = new Enemigo();
		
		DecoradorCasco enemigoConCasco = new DecoradorCasco(enemigo);
		
		DecoradorArmadura enemigoConCascoYArmadura = new DecoradorArmadura(enemigoConCasco);
		
		System.out.println(enemigoConCascoYArmadura.recibeAtaque());
		
	}

}
