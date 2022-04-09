package HDT7;

/**
 * Permite almacenar las traducciones asociadas de una palabra.
 * @author Diego Morales
 *
 */
public class Traduction {
	
	private String spanish, english, french;
	
	/**
	 * Metodo constructor
	 * @param spanish Traduccion en espanol.
	 * @param english Traduccion en ingles.
	 * @param french Traduccion en frances.
	 */
	public Traduction(String spanish, String english, String french) {
		
		this.spanish = spanish;
		this.english = english;
		this.french = french;
	}

	/**
	 * Metodo getter
	 * @return
	 */
	public String getSpanish() {
		return spanish;
	}

	/**
	 * Metodo getter
	 * @return
	 */
	public String getEnglish() {
		return english != null ? english.trim(): null;
	}

	/**
	 * Metodo getter
	 * @return
	 */
	public String getFrench() {
		return french != null ? french.trim(): null;
	}
	
	
}
