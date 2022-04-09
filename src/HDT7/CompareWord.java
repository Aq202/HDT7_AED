package HDT7;

import java.util.Comparator;
/**
 * Clase que compara dos Strings.
 * @author Diego Morales
 *
 */
public class CompareWord implements Comparator<String>{


	@Override
	public int compare(String w1, String w2) {

		return w1.compareToIgnoreCase(w2) ;
	}
	
	
}
