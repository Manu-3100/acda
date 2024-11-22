package main;

public class DecoradorCasco extends EnemigoDecorator{

	public DecoradorCasco(IEnemigo enemigo) {
		super(enemigo);
	}
	
	@Override
	public double recibeAtaque() {
		return enemigo.recibeAtaque() / 2;
	}
	
}
