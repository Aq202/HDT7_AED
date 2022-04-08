package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import HDT7.BinarySearchTree;
import HDT7.CompareWord;
import HDT7.Traduction;
import HDT7.DictionaryTraversal;
import HDT7.FileController;
import HDT7.StoreDictionaryTraversal;

class BinaryTreeTests {

	@Test
	void insertTest() {
		

		BinarySearchTree<String, Traduction> englishDictionary = new BinarySearchTree<String, Traduction>(
				new CompareWord());

		englishDictionary.insert("house", new Traduction("casa", "house", "loger"));
		englishDictionary.insert("woman", new Traduction("mujer", "woman", "femme"));
		englishDictionary.insert("town", new Traduction("pueblo", "town", "ville"));
		englishDictionary.insert("dog", new Traduction("perro", "dog", "chien"));
		englishDictionary.insert("computer", new Traduction("computadora", "computer", "ordinateur"));

		assertEquals(5, englishDictionary.count());

	}

	@Test
	void findTest() {
		BinarySearchTree<String, Traduction> englishDictionary = new BinarySearchTree<String, Traduction>(
				new CompareWord());

		englishDictionary.insert("house", new Traduction("casa", "house", "loger"));
		englishDictionary.insert("woman", new Traduction("mujer", "woman", "femme"));
		englishDictionary.insert("town", new Traduction("pueblo", "town", "ville"));
		englishDictionary.insert("dog", new Traduction("perro", "dog", "chien"));
		
		
		assertEquals("casa", englishDictionary.find("house").getSpanish());
		assertEquals(null, englishDictionary.find("chien"));
	}

}
