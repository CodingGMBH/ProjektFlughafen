package It.fallmerayer.codingGmbH.projektFlughafen.Model;

public class Flugzeug {

	// Attribute:

	private String name;
	private int anzahlSitzplaetze;
	private String flugGesellschaft;
	private double gepaeckKapazitaet;


	// Konstruktoren:

	public Flugzeug(final String name, final int anzahlSitzplaetze, final String flugGesellschaft, final double gepaeckKapazitaet) {
		this.name = name;
		this.anzahlSitzplaetze = anzahlSitzplaetze;
		this.flugGesellschaft = flugGesellschaft;
		this.gepaeckKapazitaet = gepaeckKapazitaet;
	}

	public Flugzeug(final int anzahlSitzplaetze, final String flugGesellschaft, final double gepaeckKapazitaet) {
		this.name = "";		// Momentan im System nicht vorgesehen, fuer Erweiterungen allerdings offen.
		this.anzahlSitzplaetze = anzahlSitzplaetze;
		this.flugGesellschaft = flugGesellschaft;
		this.gepaeckKapazitaet = gepaeckKapazitaet;
	}


	// Methoden:

	// Getter- und Setter-Methoden:

	// Getter-Methoden:

	public String getName() {
		return this.name;
	}

	public int getAnzahlSitzplaetze() {
		return this.anzahlSitzplaetze;
	}

	public String getFlugGesellschaft() {
		return this.flugGesellschaft;
	}

	public double getGepaeckKapazitaet() {
		return this.gepaeckKapazitaet;
	}

	// Setter-Methoden:

	public void setName(String name) {
		this.name = name;
	}

	public void setAnzahlSitzplaetze(int anzahlSitzplaetze) {
		this.anzahlSitzplaetze = anzahlSitzplaetze;
	}

	public void setFlugGesellschaft(String flugGesellschaft) {
		this.flugGesellschaft = flugGesellschaft;
	}

	public void setGepaeckKapazitaet(double gepaeckKapazitaet) {
		this.gepaeckKapazitaet = gepaeckKapazitaet;
	}

	// Weitere Methoden:

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + anzahlSitzplaetze;
		result = (prime * result) + ((flugGesellschaft == null) ? 0 : flugGesellschaft.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gepaeckKapazitaet);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Flugzeug other = (Flugzeug) obj;
		if (anzahlSitzplaetze != other.anzahlSitzplaetze) {
			return false;
		}
		if (flugGesellschaft == null) {
			if (other.flugGesellschaft != null) {
				return false;
			}
		} else if (!flugGesellschaft.equals(other.flugGesellschaft)) {
			return false;
		}
		if (Double.doubleToLongBits(gepaeckKapazitaet) != Double.doubleToLongBits(other.gepaeckKapazitaet)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}