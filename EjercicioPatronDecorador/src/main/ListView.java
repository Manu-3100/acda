package main;

public class ListView implements VisualComponent{

	private String name = "ListView";
	
	public void draw() {
		System.out.println("Dibujando " + this.name);
	}
	
}
