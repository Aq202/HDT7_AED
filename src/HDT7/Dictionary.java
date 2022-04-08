package HDT7;

public class Dictionary {
	
	private BinarySearchTree<String, Definitions> englishDictionary;
	private BinarySearchTree<String, Definitions> frenchDictionary;
	
	public Dictionary() {
		
		CompareWord comparator = new CompareWord();
		
		englishDictionary = new BinarySearchTree<>(comparator);
		frenchDictionary = new BinarySearchTree<>(comparator);
		
	}
	
	
}
