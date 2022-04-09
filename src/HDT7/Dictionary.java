package HDT7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary {

	private BinarySearchTree<String, Traduction> englishDictionary;
	private BinarySearchTree<String, Traduction> frenchDictionary;

	private FileController dictionaryFile;

	public Dictionary(String dictionaryFilePath) throws Exception {

		CompareWord comparator = new CompareWord();

		englishDictionary = new BinarySearchTree<>(comparator);
		frenchDictionary = new BinarySearchTree<>(comparator);
		dictionaryFile = new FileController(dictionaryFilePath);

		if (!dictionaryFile.exists())
			throw new FileNotFoundException("El archivo diccionario no existe");

		loadDictionary();
	}

	public Dictionary() throws Exception {
		this(FileController.DICTIONARY_PATH);
	}

	public void loadDictionary() throws Exception {

		String[] content = dictionaryFile.readFile();

		for (String line : content) {

			// english, spanish, french
			String[] words = line.split(",");

			try {

				newWord(words[0], words[1], words[2]);

			} catch (Exception ex) {
				throw new Exception("El diccionario del archivo no es valido.");
			}

		}

	}

	public void newWord(String english, String spanish, String french) throws Exception {

		Traduction traduction = new Traduction(spanish, english, french);
		if (englishDictionary.containsKey(english))
			throw new Exception("La palabra en ingles ya esta registrada.");
		if (frenchDictionary.containsKey(french))
			throw new Exception("La palabra en frances ya esta registrada.");

		englishDictionary.insert(traduction.getEnglish(), traduction);
		frenchDictionary.insert(traduction.getFrench(), traduction);
	}

	private Traduction searchWord(String word) throws Exception {

		Traduction traduction = englishDictionary.find(word);
		if (traduction == null)
			traduction = frenchDictionary.find(word);

		return traduction;

	}

	public void deleteWord(String word) throws Exception {

		Traduction traduction = searchWord(word);

		if (traduction == null)
			throw new Exception("La palabra ingresada no esta registrada.");

		englishDictionary.delete(traduction.getEnglish());
		frenchDictionary.delete(traduction.getFrench());
	}

	public void updateWord(String currentWord, String english, String spanish, String french) throws Exception {

		deleteWord(currentWord);
		newWord(english, spanish, french);

	}

	public String translateWord(String word) {

		try {
			Traduction traduction = searchWord(word);
			if (traduction != null)
				return traduction.getSpanish();

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public String translateText(String fileName) throws FileNotFoundException, IOException {

		var file = new FileController(FileController.PATH + fileName + ".txt");
		var result = "";

		String[] textRows = file.readFile();

		for (String row : textRows) {

			String[] matches = RegexScanner.evaluateRegex("(\\b(\\w|[αινσϊρ])+\\b)|\\S|\\s+", row);

			for (String expression : matches) {

				// si es una palabra
				if (RegexScanner.match("^(\\w|[αινσϊρ])+$", expression)) {

					String translatedWord = translateWord(expression);
					if (translatedWord == null)
						translatedWord = "*" + expression + "*";

					result += translatedWord;

				} else
					result += expression;

			}

			// salto de linea
			result += "\n";

		}

		return result.trim();
	}

	public boolean containsWord(String word) {

		if (englishDictionary.containsKey(word))
			return true;
		return frenchDictionary.containsKey(word);

	}

	public String inOrderByEnglishTraduction() throws Exception {

		DictionaryTraversal traversal = new DictionaryTraversal();
		String result = "";

		englishDictionary.inOrder(traversal);

		if (traversal.getAssociations().size() == 0)
			return null;

		for (Traduction traduction : traversal.getAssociations()) {
			result += String.format("(%s, %s)\n", traduction.getEnglish(), traduction.getSpanish());
		}
		return result;

	}

	public String inOrderByFrenchTraduction() throws Exception {

		DictionaryTraversal traversal = new DictionaryTraversal();
		String result = "";

		frenchDictionary.inOrder(traversal);

		if (traversal.getAssociations().size() == 0)
			return null;

		for (Traduction traduction : traversal.getAssociations()) {
			result += String.format("(%s, %s)\n", traduction.getFrench(), traduction.getSpanish());
		}
		return result;

	}

	public void updateDictionaryFile() throws Exception {

		
		StoreDictionaryTraversal traversal = new StoreDictionaryTraversal(dictionaryFile);
		dictionaryFile.clearFile(); //borrar contenido del archivo
		englishDictionary.inOrder(traversal); //llenar archivo 

	}

}
