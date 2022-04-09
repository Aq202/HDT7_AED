package HDT7;

import java.util.ArrayList;

public class DictionaryTraversal implements ITreeTraversal<String, Traduction>{
	
	
	private ArrayList<Traduction> associations;
	
	public DictionaryTraversal() {
		associations = new ArrayList<>();
	}

	@Override
	public void Walk(String key, Traduction value) {
		
		associations.add(value);		
	}
	
	public ArrayList<Traduction> getAssociations(){
		return associations;
	}
	
}
