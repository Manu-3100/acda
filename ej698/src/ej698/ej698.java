package ej698;

import java.io.IOException;

public class ej698 {

	static int getInt() throws IOException {

		int ret = 0;
		int c;

		while ((c = System.in.read()) > 32) {
			ret = ret * 10 + c - '0';
		}
		return ret;
	}
	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();

		int numCasos = getInt();
		int numeros;

		while (numCasos-- != 0) {

			int max = -1;
			int suma = 0;
			int valor = 0;

			numeros = getInt();
			numeros *= numeros;

			while (numeros-- != 0) {
				valor = getInt();
				if (valor > max)
					max = valor;
				suma += valor;
			}
			System.out.println(((suma - max) == max)? "SI" : "NO");		
		}
	}
}
