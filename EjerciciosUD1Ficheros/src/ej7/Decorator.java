package ej7;

public abstract class Decorator implements ILector {

	protected ILector lector;

	public Decorator(ILector lector) {
		this.lector = lector;
	}
}
