package HDT7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Clase FileController
 * 
 * @author diego Programado el 20/03/2022
 */

public class FileController {
	
	public static final String DICTIONARY_PATH = System.getProperty("user.dir") + "\\diccionario.txt";
	public static final String TEXT_PATH = System.getProperty("user.dir") + "\\texto.txt";
	
	private String filePath;
	
	public FileController(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Metodo que se encarga de obtener todas las filas del archivo diccionario.txt
	 * 
	 * @return String[]. Array con cada una de las filas de texto por casilla.
	 * @throws IOException
	 */
	public String[] readFile() throws IOException, FileNotFoundException {

		File doc = new File(filePath);

		File file = new File(filePath);
		
		BufferedReader obj = new BufferedReader(new FileReader(doc, StandardCharsets.UTF_8));
		ArrayList<String> linesList = new ArrayList<String>();

		// leer y almacenar las filas del archivo de texto
		String line;
		while ((line = obj.readLine()) != null) {
			linesList.add(line);
		}

		obj.close();

		return linesList.toArray(new String[linesList.size()]); // convertir lista a array
	}

	/**
	 * Permite anadir una nueva linea al archivo
	 * 
	 * @param text. Contenido del archivo
	 * @throws IOException
	 */
	public void writeLine(String text) throws IOException {

		File file = new File(filePath);

		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		// añadir nuevo registro
		pw.println(text);

		pw.close();

	}

	/**
	 * Permite sobreescribir el contenido del archivo.
	 * 
	 * @param text. Contenido del archivo
	 * @throws IOException
	 */
	public void writeFile(String text) throws IOException {
		File file = new File(filePath);

		FileWriter fw = new FileWriter(file);

		fw.write(text);
		fw.close();

	}

	public void clearFile() throws IOException {
		writeFile("");
	}
	
	public boolean exists() {
		File file = new File(filePath);
		return file.exists();
	}
}
