package main;

public class Main {

	public static void main(String[] args) {

		ListView listView = new ListView();
				
		ScrollDecorator scroll = new ScrollDecorator(listView);
		BorderDecorator border = new BorderDecorator(scroll);
		
		border.draw();
		border.drawBorder();
		scroll.scroll(10);
		
	}

}
