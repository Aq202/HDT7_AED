package HDT7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Dictionary {

	private BinarySearchTree<String, Traduction> englishDictionary;
	private BinarySearchTree<String, Traduction> frenchDictionary;

	private FileController dictionaryFile;

	public Dictionary() throws IOException {

		CompareWord comparator = new CompareWord();

		englishDictionary = new BinarySearchTree<>(comparator);
		frenchDictionary = new BinarySearchTree<>(comparator);
		dictionaryFile = new FileController(FileController.DICTIONARY_PATH);

		if (!dictionaryFile.exists())
			throw new FileNotFoundException("El archivo diccionario no existe");
		
		loadDictionary();
	}

	public void loadDictionary() throws FileNotFoundException, IOException {

		String[] content = dictionaryFile.readFile();

		for (String line : content) {

			// english, spanish, french
			String[] words = line.split(",");

			newWord(words[0], words[1], words[2]);

		}

	}

	public void newWord(String english, String spanish, String french) {

		Traduction traduction = new Traduction(spanish, english, french);

		englishDictionary.insert(traduction.getEnglish(), traduction);
		frenchDictionary.insert(traduction.getFrench(), traduction);
	}

	private Traduction searchWord(String word) throws Exception {

		Traduction traduction = englishDictionary.find(word);
		if(traduction == null) traduction = frenchDictionary.find(word);

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
			if(traduction != null) return traduction.getSpanish();			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	public String translateText() throws FileNotFoundException, IOException {
		
		var file = new FileController(FileController.TEXT_PATH);
		var result = "";
		

			String[] textRows = file.readFile();
			
			for(String row: textRows) {
								
				String[] matches = RegexScanner.evaluateRegex("(\\b(\\w|[αινσϊρ])+\\b)|\\S|\\s+", row);
				
				for(String expression: matches) {
					
					//si es una palabra
					if(RegexScanner.match("^(\\w|[αινσϊρ])+$", expression)) {
								
						String translatedWord = translateWord(expression);
						if(translatedWord == null) translatedWord = "*" + expression + "*";
						
						result += translatedWord;
						
					}else 
						result += expression;
					
				}
				
				//salto de linea
				result += "\n";
				
			}
			
			

		
		return result.trim();
	}
	

}
