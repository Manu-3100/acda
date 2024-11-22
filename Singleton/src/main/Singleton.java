package main;

public class Singleton {

	private static Singleton INSTANCIA;
	
	private String cnn = "Mysql";
	
	private Singleton() {
		
	}
	
	private synchronized static void createInstance() {
		if(INSTANCIA == null)
			INSTANCIA = new Singleton();
	}
	
	public static Singleton getInstance() {
		if (INSTANCIA == null)
			createInstance();
		return INSTANCIA;
	}
	
	public String getCnn() {
		return cnn;
	}
		
}