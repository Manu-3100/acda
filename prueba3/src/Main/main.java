package Main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class main {

	public static void main(String[] args) {

		final String WEB = "https://www.edu.xunta.gal/centros/iesmurallaromana/aulavirtual/my/courses.php";
		
		HttpResponse<Stream<String>> response;
		try {
			HttpClient client = HttpClient.newHttpClient();
			// Patrón builder
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(WEB)).build();
			response = client.send(request, HttpResponse.BodyHandlers.ofLines());
			response.body().forEach(System.out::println);
			
			

		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}

	}

}
