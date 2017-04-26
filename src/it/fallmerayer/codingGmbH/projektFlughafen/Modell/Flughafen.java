package it.fallmerayer.codingGmbH.projektFlughafen.Modell;

public class Flughafen {

	// Attribute:

	private String name;
	private String stadt;
	private String land;


	// Konstruktoren:

	public Flughafen(final String name, final String stadt, final String land) {
		this.name = name;
		this.stadt = stadt;
		this.land = land;
	}

	public Flughafen(final String stadt) {
		this.name = "";		// Momentan im System nicht vorgesehen, fuer Erweiterungen allerdings offen.
		this.stadt = stadt;
		this.land = "";		// Momentan im System nicht vorgesehen, fuer Erweiterungen allerdings offen.
	}


	// Methoden:

	// Getter- und Setter-Methoden:

	// Getter-Methoden:

	public String getName() {
		return this.name;
	}

	public String getStadt() {
		return this.stadt;
	}

	public String getLand() {
		return this.land;
	}

	// Setter-Methoden:

	public void setName(String name) {
		this.name = name;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public void setLand(String land) {
		this.land = land;
	}

	// Weitere Methoden:

	@Override
	public String toString() {
		return this.stadt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((land == null) ? 0 : land.hashCode());
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		result = (prime * result) + ((stadt == null) ? 0 : stadt.hashCode());
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
		Flughafen other = (Flughafen) obj;
		if (land == null) {
			if (other.land != null) {
				return false;
			}
		} else if (!land.equals(other.land)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (stadt == null) {
			if (other.stadt != null) {
				return false;
			}
		} else if (!stadt.equals(other.stadt)) {
			return false;
		}
		return true;
	}

}