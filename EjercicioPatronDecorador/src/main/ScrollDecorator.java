package main;

public class ScrollDecorator extends Decorator{
	
	public ScrollDecorator(VisualComponent component) {
		super(component);
	}

	@Override
	public void draw() {
		component.draw();
		System.out.println("Dibujando Scroll");
	}

	public void scroll(int pos) {
		System.out.println("scrolleando " + pos);
	}
	
}
