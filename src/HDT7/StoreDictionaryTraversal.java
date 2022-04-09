package HDT7;

import java.io.IOException;
import java.util.HashMap;

public class StoreDictionaryTraversal implements ITreeTraversal<String, Traduction> {

	private HashMap<String, String> associations;
	private FileController file;
	
	public StoreDictionaryTraversal(FileController file) {
		this.file = file;
		associations = new HashMap<>();
	}
	

	@Override
	public void Walk(String key, Traduction value) throws IOException {
		
		file.writeLine(value.getEnglish() + "," + value.getSpanish() + "," + value.getFrench());
		
	}
	
		

}
