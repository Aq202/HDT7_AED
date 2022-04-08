package HDT7;

import java.io.IOException;
import java.util.HashMap;

public class StoreDictionaryTraversal implements ITreeTraversal<String, Definitions> {

	private HashMap<String, String> associations;
	
	public StoreDictionaryTraversal() {
		associations = new HashMap<>();
	}
	

	@Override
	public void Walk(String key, Definitions value) throws IOException {
		
		FileController.writeLine(value.getEnglish() + "," + value.getSpanish() + "," + value.getFrench());
		
	}
	
		

}
