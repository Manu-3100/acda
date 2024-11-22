package main;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Hilo1 extends Thread{
	public static Vector<Integer> lista = new Vector<Integer>();
	public static ArrayList<Integer> lista2 = new ArrayList<Integer>();
//	private static Semaphore s = new Semaphore(1);
//	
//	private static void incrementar() {
//		
//		try {
//			s.acquire();
//			var++;
//			s.release();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void run() {
		for(int i = 0; i < 5000; i++) {
			lista.add(1);
			lista2.add(1);
		}
	}
	
	
	
}
