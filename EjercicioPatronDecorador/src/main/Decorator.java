package main;

public abstract class Decorator implements VisualComponent{

	protected VisualComponent component = new ListView();

	public Decorator(VisualComponent component) {
		this.component = component;
	}
	
	@Override
	public void draw() {
		component.draw();
	}
}
