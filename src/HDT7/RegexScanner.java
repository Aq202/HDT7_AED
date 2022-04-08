package HDT7;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexScanner {

	/**
	 * Se encarga de verificar si una expresion posee coincidencias
	 * 
	 * @param regex.     String. Expresion regular.
	 * @param expression String.
	 * @return boolean.
	 */
	public static boolean hasMatches(String regex, String expression) {
		return evaluateRegex(regex, expression).length > 0;
	}

	/**
	 * Se encarga de verificar si una expresion coincide en su totalidad con la
	 * regex.
	 * 
	 * @param regex      String. Expresion regular.
	 * @param expression String.
	 * @return boolean.
	 */
	public static boolean match(String regex, String expression) {
		String[] matches = evaluateRegex(regex, expression);

		// verificar si hay Strings en la expresion
		if (matches.length > 0) {

			// el string corresponde a toda la cadena
			if (expression.trim().equals(matches[0].trim()))
				return true;
		}
		return false;
	}

	/**
	 * Se encarga de encontrar las coincidencias con una expresion regular.
	 * 
	 * @param regex      String. Expresion regular.
	 * @param expression String.
	 * @return String[]. Arreglo con todas las coincidencias encontradas.
	 */
	public static String[] evaluateRegex(String regex, String expression) {

		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(expression);
		ArrayList<String> results = new ArrayList<>();

		while (matcher.find()) {
			results.add(matcher.group());
		}

		return results.toArray(new String[results.size()]);
	}
}
