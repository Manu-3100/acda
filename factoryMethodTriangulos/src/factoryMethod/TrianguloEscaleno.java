package factoryMethod;

public class TrianguloEscaleno extends Triangulo{

	public TrianguloEscaleno(double lado1, double lado2, double lado3) {
		super(lado1, lado2, lado3);
	}

	@Override
	public double getArea() {
		double s = (lado1 + lado2 + lado3)/2 ;
		return Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
	}

	@Override
	public String toString() {
		return "TrianguloEscaleno ";
	}

}
