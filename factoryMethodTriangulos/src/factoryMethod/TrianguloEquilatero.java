package factoryMethod;

public class TrianguloEquilatero extends Triangulo{

	public TrianguloEquilatero(double lado) {
		super(lado, lado, lado);
	}

	@Override
	public double getArea() {
		return Math.sqrt(3)/4 * Math.pow(this.lado1, 2);
	}

	@Override
	public String toString() {
		return "TrianguloEquilatero ";
	}

	
}
