package HDT7;

import java.util.ArrayList;

/**
 * Permite especificar las acciones a ejecutar en el ordenamiento del arbol.
 * @author Diego Morales
 *
 */
public class DictionaryTraversal implements ITreeTraversal<String, Traduction>{
	
	
	private ArrayList<Traduction> associations;
	
	public DictionaryTraversal() {
		associations = new ArrayList<>();
	}

	@Override
	public void Walk(String key, Traduction value) {
		
		associations.add(value);		
	}
	
	/**
	 * Retorna el contenido ordenado del diccionario.
	 * @return
	 */
	public ArrayList<Traduction> getAssociations(){
		return associations;
	}
	
}
