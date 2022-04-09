package HDT7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	/**
	 * Metodo que se encarga de solicitar y validar un numero entero.
	 * 
	 * @param sc             Scanner
	 * @param message
	 * @param absoluteValues
	 * @param validValues
	 * @return int.
	 */
	private static int getValidInt(Scanner sc, String message, boolean absoluteValues, Integer... validValues) {

		while (true) {

			try {

				System.out.println(message);
				int value = sc.nextInt();
				sc.nextLine();

				if (Arrays.asList(validValues).contains(value) || validValues.length == 0)
					return absoluteValues ? Math.abs(value) : value;
				else
					System.out.println("Por favor, ingresa un numero valido");

			} catch (Exception ex) {
				System.out.println("Por favor, ingresa un numero valido");
				sc.nextLine();
			}
		}
	}

	/**
	 * Se encarga de solicitar al usuario que ingrese un dato.
	 * 
	 * @param sc      Scanner
	 * @param message
	 * @return String.
	 */
	private static String getString(Scanner sc, String message) {
		System.out.println(message);
		return sc.nextLine();
	}

	/**
	 * Metodo principal de la clase
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Dictionary dict;

		try {
			dict = new Dictionary();
		} catch (IOException e) {
			System.out.println("El archivo diccionario.txt no existe en la ruta " + FileController.DICTIONARY_PATH);
			sc.close();
			return;
		}catch (Exception ex) {
			System.out.println("El archivo contiene un diccionario invalido.");
			sc.close();
			return;
		}

		boolean stop = false;
		while (!stop) {

			int option = getValidInt(sc, """
					\nSelecciona una opcion:
					1. Nueva traduccion.
					2. Editar traduccion.
					3. Eliminar traduccion.
					4. Traducir archivo.
					5. Palabras ordenadas segun traduccion en ingles.
					6. Palabras ordenadas segun traduccion en frances.
					7. Salir.

					""", true, 1, 2, 3, 4, 5, 6, 7);

			switch (option) {

			case 1: {

				
				try {
					dict.newWord(getString(sc, "Ingresa la palabra en ingles: "),
							getString(sc, "Ingresa la palabra en espanol: "),
							getString(sc, "Ingresa la palabra en frances: "));
				

				System.out.println("\nTraducción incluida correctamente!");
				
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			case 2: {

				try {

					String wordToModify = getString(sc, "Ingresa la palabra a modificar: ");

					if (dict.containsWord(wordToModify)) {

						dict.updateWord(wordToModify, getString(sc, "Ingresa la palabra en ingles: "),
								getString(sc, "Ingresa la palabra en espanol: "),
								getString(sc, "Ingresa la palabra en frances: "));

						System.out.println("\nTraduccion modificada exitosamente.");
						break;
					}
				} catch (Exception e) {
				}
				System.out.println("\nLa palabra ingresada no esta incluida en el diccionario.");

				break;
			}
			case 3: {
				try {

					String wordToDelete = getString(sc, "Ingresa la palabra a eliminar: ");
					dict.deleteWord(wordToDelete);

					System.out.println("\nTraduccion eliminada correctamente!");

				} catch (Exception e) {
					System.out.println("\nLa palabra ingresada no esta incluida en el diccionario.");
				}
				break;
			}
			case 4: {

				String fileName = getString(sc, "Ingresar nombre del archivo(.txt) a traducir:");

				try {
					String traduction = dict.translateText(fileName);
					System.out.println("\nTraduccion completada: ");
					System.out.println(traduction);

				} catch (IOException e) {
					System.out
							.println(String.format("El archivo %s.txt no existe en %s", fileName, FileController.PATH));
				}

				break;
			}
			case 5: {

				String result;
				try {
					result = dict.inOrderByEnglishTraduction();

					System.out.println("\nTraducciones en ingles in-order:");

					if (result == null)
						System.out.println("El diccionario se encuentra vacio.");
					else
						System.out.println(result);

				} catch (Exception e) {
				}

				break;
			}
			case 6: {

				String result;
				try {
					result = dict.inOrderByFrenchTraduction();

					System.out.println("\nTraducciones en frances in-order:");

					if (result == null)
						System.out.println("El diccionario se encuentra vacio.");
					else
						System.out.println(result);

				} catch (Exception e) {
				}

				break;
			}

			case 7: {

				stop = true;
				try {
					dict.updateDictionaryFile();
				} catch (Exception e) {
				}
				break;
			}

			}

		}

		System.out.println("Hasta pronto!");

	}

}
