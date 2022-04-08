package HDT7;

import java.io.IOException;
import java.util.HashMap;

public class StoreDictionaryTraversal implements ITreeTraversal<String, Traduction> {

	private HashMap<String, String> associations;
	
	public StoreDictionaryTraversal() {
		associations = new HashMap<>();
	}
	

	@Override
	public void Walk(String key, Traduction value) throws IOException {
		
		var file = new FileController(FileController.DICTIONARY_PATH);
		file.writeLine(value.getEnglish() + "," + value.getSpanish() + "," + value.getFrench());
		
	}
	
		

}
