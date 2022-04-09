package HDT7;

public class Traduction {
	
	private String spanish, english, french;
	
	public Traduction(String spanish, String english, String french) {
		
		this.spanish = spanish;
		this.english = english;
		this.french = french;
	}

	public String getSpanish() {
		return spanish;
	}

	public String getEnglish() {
		return english != null ? english.trim(): null;
	}

	public String getFrench() {
		return french != null ? french.trim(): null;
	}
	
	
}
