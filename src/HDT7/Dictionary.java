package HDT7;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase que se encarga de manejar un diccionario de ingles, espanol y frances.
 * @author Diego Morales
 *
 */
public class Dictionary {

	private BinarySearchTree<String, Traduction> englishDictionary;
	private BinarySearchTree<String, Traduction> frenchDictionary;

	private FileController dictionaryFile;

	/**
	 * Metodo constructor de la clase.
	 * @param dictionaryFilePath String. Ruta del archivo que contine las asociaciones de palabras.
	 * @throws Exception
	 */
	public Dictionary(String dictionaryFilePath) throws Exception {

		CompareWord comparator = new CompareWord();

		englishDictionary = new BinarySearchTree<>(comparator);
		frenchDictionary = new BinarySearchTree<>(comparator);
		dictionaryFile = new FileController(dictionaryFilePath);

		if (!dictionaryFile.exists())
			throw new FileNotFoundException("El archivo diccionario no existe");

		loadDictionary();
	}

	/**
	 * Metodo constructor de la clase
	 * @throws Exception
	 */
	public Dictionary() throws Exception {
		this(FileController.DICTIONARY_PATH);
	}

	/**
	 * Se encarga de obtener el contenido almacenado en el archivo y cargarlo al programa.
	 * @throws Exception
	 */
	private void loadDictionary() throws Exception {

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

	/**
	 * Permite agregar una nueva traduccion al diccionario.
	 * @param english String. Traduccion en ingles.
	 * @param spanish String. Traduccion en espanol.
	 * @param french String. Traduccion en frances.
	 * @throws Exception
	 */
	public void newWord(String english, String spanish, String french) throws Exception {

		Traduction traduction = new Traduction(spanish, english, french);
		if (englishDictionary.containsKey(english))
			throw new Exception("La palabra en ingles ya esta registrada.");
		if (frenchDictionary.containsKey(french))
			throw new Exception("La palabra en frances ya esta registrada.");

		englishDictionary.insert(traduction.getEnglish(), traduction);
		frenchDictionary.insert(traduction.getFrench(), traduction);
	}

	/**
	 * Permite buscar una palabra en ambos diccionarios.
	 * @param word String. Palabra en ingles o frances.
	 * @return Traduction.
	 * @throws Exception
	 */
	private Traduction searchWord(String word) throws Exception {

		Traduction traduction = englishDictionary.find(word);
		if (traduction == null)
			traduction = frenchDictionary.find(word);

		return traduction;

	}

	/**
	 * Se encarga de buscar y eliminar una palabra en ambos diccionarios.
	 * @param word String. Palabra en ingles o frances.
	 * @throws Exception
	 */
	public void deleteWord(String word) throws Exception {

		Traduction traduction = searchWord(word);

		if (traduction == null)
			throw new Exception("La palabra ingresada no esta registrada.");

		englishDictionary.delete(traduction.getEnglish());
		frenchDictionary.delete(traduction.getFrench());
	}

	/**
	 * Se encarga de actualizar el contenido de una traduccion determinada.
	 * @param currentWord String. Palabra a actualizar en ingles o frances.
	 * @param english String. Nueva traduccion en ingles.
	 * @param spanish String. Nueva traduccion en espanol.
	 * @param french String. Nueva traduccion en frances.
	 * @throws Exception
	 */
	public void updateWord(String currentWord, String english, String spanish, String french) throws Exception {

		deleteWord(currentWord);
		newWord(english, spanish, french);

	}

	/**
	 * Permite obtener la traduccion en espanol de una determinada palabra.
	 * @param word String. Palabra en ingles o frances a traducir.
	 * @return
	 */
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

	/**
	 * Se encarga de cargar el contenido de un archivo y traducir su contenido.
	 * @param fileName Nombre del archivo contenido en la ruta de ejecucion actual. Solo se debe de adjuntar el nombre del archivo sin la extension .txt
	 * @return String. Traduccion completa.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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

	/**
	 * Permite determinar si una palabra existe en alguno de los diccionarios.
	 * @param word Palabra en ingles o frances.
	 * @return boolean.
	 */
	public boolean containsWord(String word) {

		if (englishDictionary.containsKey(word))
			return true;
		return frenchDictionary.containsKey(word);

	}

	/**
	 * Retorna el contenido del diccionario en ingles inorder.
	 * @return String.
	 * @throws Exception
	 */
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

	/**
	 * Retorna el contenido del diccionario en espanol inorder.
	 * @return String.
	 * @throws Exception
	 */
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

	/**
	 * Se encarga de sobreescribir el contenido del archivo del diccionario con la data generada en la ejecucion actual del programa.
	 * @throws Exception
	 */
	public void updateDictionaryFile() throws Exception {

		
		StoreDictionaryTraversal traversal = new StoreDictionaryTraversal(dictionaryFile);
		dictionaryFile.clearFile(); //borrar contenido del archivo
		englishDictionary.inOrder(traversal); //llenar archivo 

	}

}
