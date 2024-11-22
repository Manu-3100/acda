package main;

public class DecoradorArmadura extends EnemigoDecorator{

	public DecoradorArmadura(IEnemigo enemigo) {
		super(enemigo);
	}
	
	@Override
	public double recibeAtaque() {	
		return enemigo.recibeAtaque() / 1.5;
	}
	
	

}
