package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import HDT7.Dictionary;
import HDT7.FileController;

class DictionaryTests {
	
	/**
	 * Test para metodo de agregar una nueva traduccion.
	 */
	@Test 
	void newWordTest(){
		
		try {
			
			var dict = new Dictionary();
			dict.newWord("noise", "ruido", "bruit");
			
						
			assertEquals("ruido", dict.translateWord("noise"));
			assertEquals("ruido", dict.translateWord("bruit"));

			
		}catch(Exception ex) {
			
		}
		
	}

	@Test
	void translateWordTest() {
		
		try {
			var dict = new Dictionary();
			
			dict.newWord("house", "casa", "maison");
			
			
			assertEquals("casa", dict.translateWord("house"));
			assertEquals("casa", dict.translateWord("Maison"));
			
		} catch (FileNotFoundException e) {
			fail("Catch: " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	@Test
	void translateTextTest() {
		
		try {
			var dict = new Dictionary(FileController.PATH + "dictionaryTest.txt");
			
			dict.newWord("woman", "mujer", "afds");
			dict.newWord("homework", "tarea", "maison");
			dict.newWord("town", "pueblo", "maison");
			
			assertEquals("*The* mujer *asked* *me* *to* *do* *my* tarea *about* *my* pueblo.", dict.translateText("textTest"));
			
			
		}catch(Exception ex) {
			fail("Catch " + ex);
		}
	}

}
