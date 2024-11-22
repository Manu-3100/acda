package capaNegocio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ErrorLogAnalyzer implements LogAnalyzer {

	private List<String> res;
	private Map<String, Integer> map = new TreeMap<String, Integer>();

	public ErrorLogAnalyzer(String file) {
		res = new ArrayList<String>();
		String linea;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			while ((linea = br.readLine()) != null) {
				linea = linea.substring(linea.lastIndexOf("- ") + 2);
				
				if(map.containsKey(linea))
					map.replace(linea, map.get(linea) + 1);
				else
					map.put(linea, 1);
			}
			
			for(Map.Entry<String, Integer> entry: map.entrySet()) {
				res.add(entry.getKey() + ": " + entry.getValue());
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
