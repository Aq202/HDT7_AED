/**
 * 
 */
package HDT7;

import java.io.IOException;

/**
 * Clase elaborada en el curso Algoritmos y Estructuras de Datos UVG.
 * @author MOISES ANTONIO ALONSO GONZALEZ
 *
 */
public interface ITreeTraversal<K,V> {

	void Walk(K key, V value) throws Exception;
	
}
