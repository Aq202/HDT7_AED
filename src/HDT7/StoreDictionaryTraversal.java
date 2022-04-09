package HDT7;

import java.io.IOException;
import java.util.HashMap;

/**
 * Permite especificar la accion de almacenar los registros en el archivo durante el ordenamiento del arbol.
 * @author diego
 *
 */
public class StoreDictionaryTraversal implements ITreeTraversal<String, Traduction> {

	private HashMap<String, String> associations;
	private FileController file;
	
	/**
	 * Metodo constructor
	 * @param file Objeto FileController del archivo diccionario.
	 */
	public StoreDictionaryTraversal(FileController file) {
		this.file = file;
		associations = new HashMap<>();
	}
	

	@Override
	public void Walk(String key, Traduction value) throws IOException {
		
		file.writeLine(value.getEnglish() + "," + value.getSpanish() + "," + value.getFrench());
		
	}
	
		

}
