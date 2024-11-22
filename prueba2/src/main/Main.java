package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

	static int getInt() throws IOException {

		int ret = 0;
		int c;

		while ((c = System.in.read()) != 10) {
			ret = ret * 10 + (c - '0');
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		
		System.out.println( getInt() );
		
	}

}
