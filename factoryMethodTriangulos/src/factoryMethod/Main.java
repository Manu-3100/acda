package factoryMethod;

import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        ConcreteCreator fabrica = new ConcreteCreator();

        try {
            Triangulo triangulo = fabrica.factoryMethod(5, 5, 5);
            System.out.println(triangulo.toString());
            System.out.println(triangulo.getArea());
            System.out.println();

            triangulo = fabrica.factoryMethod(5, 6, 6);
            System.out.println(triangulo.toString());
            System.out.println(triangulo.getArea());
            System.out.println();

            triangulo = fabrica.factoryMethod(1, 2, 5);
            System.out.println(triangulo.toString());
            System.out.println(triangulo.getArea());
            System.out.println();
            
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }

    }
}
