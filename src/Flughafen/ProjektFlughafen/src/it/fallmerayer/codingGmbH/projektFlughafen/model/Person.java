package it.fallmerayer.codingGmbH.projektFlughafen.model;

import java.util.Date;

public class Person {

	// Attribute:

	private String vorname;
	private String nachname;
	private String email;
	private Date geburtsDatum;


	// Konstruktor:

	public Person(final String vorname, final String nachname, final String email, final Date geburtsDatum) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.geburtsDatum = geburtsDatum;
	}


	// Methoden:

	// Getter- und Setter-Methoden:

	// Getter-Methoden:

	public String getVorname() {
		return this.vorname;
	}

	public String getNachname() {
		return this.nachname;
	}

	public String getEmail() {
		return this.email;
	}

	public Date getGeburtsDatum() {
		return this.geburtsDatum;
	}

	// Setter-Methoden:

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGeburtsDatum(Date geburtsDatum) {
		this.geburtsDatum = geburtsDatum;
	}

	// Weitere Methoden:

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((email == null) ? 0 : email.hashCode());
		result = (prime * result) + ((geburtsDatum == null) ? 0 : geburtsDatum.hashCode());
		result = (prime * result) + ((nachname == null) ? 0 : nachname.hashCode());
		result = (prime * result) + ((vorname == null) ? 0 : vorname.hashCode());
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
		Person other = (Person) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (geburtsDatum == null) {
			if (other.geburtsDatum != null) {
				return false;
			}
		} else if (!geburtsDatum.equals(other.geburtsDatum)) {
			return false;
		}
		if (nachname == null) {
			if (other.nachname != null) {
				return false;
			}
		} else if (!nachname.equals(other.nachname)) {
			return false;
		}
		if (vorname == null) {
			if (other.vorname != null) {
				return false;
			}
		} else if (!vorname.equals(other.vorname)) {
			return false;
		}
		return true;
	}

}