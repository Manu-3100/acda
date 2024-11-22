package factoryMethod;

public class TrianguloIsosceles extends Triangulo{

	public TrianguloIsosceles(double lado1, double lado2, double lado3) {
		super(lado1, lado2, lado3);
	}

	
	public double getArea() {
		double a, b;
		if (lado1 == lado2) {
			a = lado1;
			b = lado3;
		} else if (lado1== lado3) {
			a = lado1;
			b = lado2;
		}
		else{
			a = lado3;
			b = lado1;
		}
		return (b * Math.sqrt(Math.pow(a, 2) -  (Math.pow(b, 2) / 4 ))) / 2 ;

	}

	@Override
	public String toString() {
		return "TrianguloIsosceles ";
	}
}
