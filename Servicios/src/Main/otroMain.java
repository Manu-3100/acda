package Main;

public class otroMain {

	public static void main(String[] args) {
		
		ProcessBuilder prb = new ProcessBuilder("notepad.exe", "C:\\Users\\ferdebman\\Desktop\\Programacion.txt");
		
		try {
			
			Process proceso = prb.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
