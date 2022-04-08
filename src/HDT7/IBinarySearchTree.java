/**
 * 
 */
package HDT7;

import java.util.ArrayList;

/**
 * @author MAAG
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
