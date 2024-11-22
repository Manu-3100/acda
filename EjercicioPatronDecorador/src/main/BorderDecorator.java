package main;

public class BorderDecorator extends Decorator{
	
	public BorderDecorator(VisualComponent component) {
		super(component);
	}

	@Override
	public void draw() {
		component.draw();
		System.out.println("Añadiendo borde");
	}
	
	public void drawBorder() {
		System.out.println("Añadiendo 10 cm de borde");
	}
	
}
