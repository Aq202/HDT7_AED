package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import HDT7.BinarySearchTree;
import HDT7.CompareWord;
import HDT7.Definitions;
import HDT7.DictionaryTraversal;
import HDT7.FileController;
import HDT7.StoreDictionaryTraversal;

class BinaryTreeTests {

	@Test
	void insertTest() {
		
		var file = new FileController(FileController.DICTIONARY_PATH);
		
		try {
			file.clearFile();
		} catch (IOException e1) {
			fail(e1);
		}

		BinarySearchTree<String, Definitions> englishDictionary = new BinarySearchTree<String, Definitions>(
				new CompareWord());

		englishDictionary.insert("house", new Definitions("casa", "house", "loger"));
		englishDictionary.insert("woman", new Definitions("mujer", "woman", "femme"));
		englishDictionary.insert("town", new Definitions("pueblo", "town", "ville"));
		englishDictionary.insert("dog", new Definitions("perro", "dog", "chien"));
		englishDictionary.insert("computer", new Definitions("computadora", "computer", "ordinateur"));

		var dictionaryTraversal = new StoreDictionaryTraversal();

		try {
			englishDictionary.inOrder(dictionaryTraversal);
		} catch (IOException e) {
			fail("El archivo diccionario.txt no existe.");
		} catch (Exception e) {
			fail(e);
		}

	}

	@Test
	void findTest() {
		BinarySearchTree<String, Definitions> englishDictionary = new BinarySearchTree<String, Definitions>(
				new CompareWord());

		englishDictionary.insert("house", new Definitions("casa", "house", "loger"));
		englishDictionary.insert("woman", new Definitions("mujer", "woman", "femme"));
		englishDictionary.insert("town", new Definitions("pueblo", "town", "ville"));
		englishDictionary.insert("dog", new Definitions("perro", "dog", "chien"));
		
		
		assertEquals(true, englishDictionary.containsKey("house"));
		assertEquals(false, englishDictionary.containsKey("chien"));
	}

}
