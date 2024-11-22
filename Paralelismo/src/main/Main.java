package main;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//		Hilo1 h1 = new Hilo1();
//		
//		h1.start();
//		
//		h1.join();
		
		int threadCount = Runtime. getRuntime(). availableProcessors();
		
		System.out.println(threadCount);
	}

}
