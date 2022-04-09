/**
 * 
 */
package HDT7;

import java.util.ArrayList;

/**
 * Clase elaborada en el curso Algoritmos y Estructuras de Datos UVG.
 * @author MOISES ANTONIO ALONSO GONZALEZ
 *
 */
public interface IBinarySearchTree<K, V> {

	void insert(K id, V value);

	V delete(K id);

	V find(K id);

	int count();

	boolean isEmpty();
	
	boolean containsKey(K id);

	ArrayList<V> getElements();

	void inOrder(ITreeTraversal<K, V> traversal) throws Exception;

	void preOrder(ITreeTraversal<K, V> traversal) throws Exception;

	void postOrder(ITreeTraversal<K, V> traversal) throws Exception;

}
