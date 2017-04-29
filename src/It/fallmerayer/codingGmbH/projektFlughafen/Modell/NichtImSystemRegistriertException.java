package It.fallmerayer.codingGmbH.projektFlughafen.Modell;

public class NichtImSystemRegistriertException extends Exception {

	// Attribut:

	private String benutzerName;


	// Konstruktoren:

	public NichtImSystemRegistriertException() {
		super();
	}

	public NichtImSystemRegistriertException(final String benutzerName) {
		super();
		this.benutzerName = benutzerName;
	}

	public NichtImSystemRegistriertException(final String nachricht, final String benutzerName) {
		super(nachricht);
		this.benutzerName = benutzerName;
	}


	// Methoden:

	// Getter- und Setter-Methode:

	// Getter-Methode:

	public String getBenutzerName() {
		return this.benutzerName;
	}

	// Setter-Methode:

	public void setBenutzerName(String benutzerName) {
		this.benutzerName = benutzerName;
	}

}