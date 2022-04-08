package HDT7;

import java.util.ArrayList;

public class DictionaryTraversal implements ITreeTraversal<String, Definitions>{
	
	
	private ArrayList<String> associations;
	
	public DictionaryTraversal() {
		associations = new ArrayList<>();
	}

	@Override
	public void Walk(String key, Definitions value) {
		
		associations.add(key + ", "  + value.getSpanish());		
	}
	
	public ArrayList<String> getAssociations(){
		return associations;
	}
	
}
