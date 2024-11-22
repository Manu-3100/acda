package capaNegocio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserActivityLogAnalyzer implements LogAnalyzer{
	
	private List<String> res;
	
	public UserActivityLogAnalyzer(String file) {
		res = new ArrayList<String>();
		String linea;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((linea = br.readLine()) != null) {
				res.add(linea);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<String> analiza() {
		return res;
	}

}
