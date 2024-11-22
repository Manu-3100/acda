package ej7;

import java.io.File;
import java.util.List;

public interface ILector {

	public List<String> leer (String fileName);
	public List<String> leer (File file);
	
}
