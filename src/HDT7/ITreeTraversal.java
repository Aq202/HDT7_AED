/**
 * 
 */
package HDT7;

import java.io.IOException;

/**
 * @author MAAG
 *
 */
public interface ITreeTraversal<K,V> {

	void Walk(K key, V value) throws Exception;
	
}
