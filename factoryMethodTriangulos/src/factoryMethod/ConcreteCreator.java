package factoryMethod;

import java.security.InvalidParameterException;

public class ConcreteCreator extends Creator {

    @Override
    public Triangulo factoryMethod(double lado1, double lado2, double lado3) {
        // si es un triangulo
        double mayor;
        double a, b;
        if (lado1 > lado2)
            mayor = (lado1 > lado3) ? lado1 :lado2;
        else
            mayor = (lado2 > lado3) ? lado2 : lado3;
        
        if (lado1 + lado2 + lado3 - mayor > mayor ) {

            if (lado1 == lado2 && lado1 == lado3) {
                return new TrianguloEquilatero(lado1);

            } else if (lado1 != lado2 && lado1 != lado3 && lado2 != lado3) {
                return new TrianguloEscaleno(lado1, lado2, lado3);

            } else {
                return new TrianguloIsosceles(lado1, lado2, lado3);
            }
        }else{
            throw new InvalidParameterException("Los lados no forman un triangulo");
        }
         

    }

}
