package main;

public abstract class EnemigoDecorator implements IEnemigo{

	protected IEnemigo enemigo;
	
	public EnemigoDecorator(IEnemigo enemigo) {
		this.enemigo = enemigo;
	}

}
