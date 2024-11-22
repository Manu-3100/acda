package ej8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class BufferedReader implements AutoCloseable{

	private Reader r;
	
	public BufferedReader(InputStreamReader isr) {
		this.r = isr;
	}
	
	public String readLine() throws IOException {

		String cadena = null;
		int c = r.read();
		
		if (c != -1) {
			StringBuilder sb = new StringBuilder();
			
			do {
				sb.append((char) c);
			} while ((c = r.read()) != 10 && c != -1);
			
			cadena = sb.toString();
			if	(cadena.charAt(cadena.length() - 1) == '\r')
				cadena = cadena.substring(0, cadena.length() - 1 );
		}
		return cadena;
	}
	
	@Override
	public void close() throws Exception {
		r.close();
	}
}