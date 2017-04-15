package it.fallmerayer.codingGmbH.projektFlughafen.model;

import java.util.Date;

public class Flug {

	// Attribute:

	private Flugzeug flugzeug;
	private String flugNummer;
	private Flughafen startFlughafen;
	private Flughafen zielFlughafen;
	private int preisSitzplatz;
	private Date abflugZeit;
	private Date ankunftZeit;
	private int zaehlerGebuchteSitzplaetze;
	private double zaehlerGepaeckGewicht;
	private boolean buchbar;


	// Konstruktor:

	public Flug(final Flugzeug flugzeug, final String flugNummer, final Flughafen startFlughafen, final Flughafen zielFlughafen, final int preisSitzplatz, final Date abflugZeit, final Date ankunftZeit) {
		this.flugzeug = flugzeug;
		this.flugNummer = flugNummer;
		this.startFlughafen = startFlughafen;
		this.zielFlughafen = zielFlughafen;
		this.preisSitzplatz = preisSitzplatz;
		this.abflugZeit = abflugZeit;
		this.ankunftZeit = ankunftZeit;
		this.zaehlerGebuchteSitzplaetze = 0;		// Beim Erstellen eines Fluges ist noch kein Sitzplatz gebucht.
		this.zaehlerGepaeckGewicht = 0;			// Beim Erstellen eines Fluges ist noch ein Gepaeck fuer diesen Flug angegeben worden.
		this.buchbar = true;		// Jeder neu erstellte Flug ist buchbar.
	}


	// Methoden:

	// Getter- und Setter-Methoden:

	// Getter-Methoden:

	public Flugzeug getFlugzeug() {
		return this.flugzeug;
	}

	public String getFlugNummer() {
		return this.flugNummer;
	}

	public Flughafen getStartFlughafen() {
		return this.startFlughafen;
	}

	public Flughafen getZielFlughafen() {
		return this.zielFlughafen;
	}

	public int getPreisSitzplatz() {
		return this.preisSitzplatz;
	}

	public Date getAbflugZeit() {
		return this.abflugZeit;
	}

	public Date getAnkunftZeit() {
		return this.ankunftZeit;
	}

	public int getZaehlerGebuchteSitzplaetze() {
		return this.zaehlerGebuchteSitzplaetze;
	}

	public double getZaehlerGepaeckGewicht() {
		return this.zaehlerGepaeckGewicht;
	}

	public boolean isBuchbar() {
		return this.buchbar;
	}

	// Setter-Methoden:

	public void setFlugzeug(Flugzeug flugzeug) {
		this.flugzeug = flugzeug;
	}

	public void setFlugNummer(String flugNummer) {
		this.flugNummer = flugNummer;
	}

	public void setStartFlughafen(Flughafen startFlughafen) {
		this.startFlughafen = startFlughafen;
	}

	public void setZielFlughafen(Flughafen zielFlughafen) {
		this.zielFlughafen = zielFlughafen;
	}

	public void setPreisSitzplatz(int preisSitzplatz) {
		this.preisSitzplatz = preisSitzplatz;
	}

	public void setAbflugZeit(Date abflugZeit) {
		this.abflugZeit = abflugZeit;
	}

	public void setAnkunftZeit(Date ankunftZeit) {
		this.ankunftZeit = ankunftZeit;
	}

	public void setZaehlerGebuchteSitzplaetze(int zaehlerGebuchteSitzplaetze) {
		this.zaehlerGebuchteSitzplaetze = zaehlerGebuchteSitzplaetze;
	}

	public void setZaehlerGepaeckGewicht(double zaehlerGepaeckGewicht) {
		this.zaehlerGepaeckGewicht = zaehlerGepaeckGewicht;
	}

	public void setBuchbar(boolean buchbar) {
		this.buchbar = buchbar;
	}

	// Weitere Methoden:

	public double getDauerFlug() {
		return ((double) (this.ankunftZeit.getTime() - this.abflugZeit.getTime())) / (1000 * 60 * 60);		// getTime()-Methode: Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by the Date-Object. Die Anzahl der Milli-Sekunden wird als Long zurueckgegeben, weshalb der Long noch in einen Double gecastet werden muss, damit es nicht zu einer Ganzzahligen Division, bei der die Nachkommastellen verloren gehen, kommen kann. Durch das Dividieren dieser Zahl durch (1000 * 60 * 60) werden die Milli-Sekunden in Stunden umgerechnet.
	}

	public double getFreiesGepaeckGewicht() {
		return this.flugzeug.getGepaeckKapazitaet() - this.zaehlerGepaeckGewicht;
	}

	public int getFreiePlaetze() {
		return this.flugzeug.getAnzahlSitzplaetze() - this.zaehlerGebuchteSitzplaetze;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((abflugZeit == null) ? 0 : abflugZeit.hashCode());
		result = (prime * result) + ((ankunftZeit == null) ? 0 : ankunftZeit.hashCode());
		result = (prime * result) + (buchbar ? 1231 : 1237);
		result = (prime * result) + ((flugNummer == null) ? 0 : flugNummer.hashCode());
		result = (prime * result) + ((flugzeug == null) ? 0 : flugzeug.hashCode());
		result = (prime * result) + preisSitzplatz;
		result = (prime * result) + ((startFlughafen == null) ? 0 : startFlughafen.hashCode());
		result = (prime * result) + zaehlerGebuchteSitzplaetze;
		long temp;
		temp = Double.doubleToLongBits(zaehlerGepaeckGewicht);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		result = (prime * result) + ((zielFlughafen == null) ? 0 : zielFlughafen.hashCode());
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
		Flug other = (Flug) obj;
		if (abflugZeit == null) {
			if (other.abflugZeit != null) {
				return false;
			}
		} else if (!abflugZeit.equals(other.abflugZeit)) {
			return false;
		}
		if (ankunftZeit == null) {
			if (other.ankunftZeit != null) {
				return false;
			}
		} else if (!ankunftZeit.equals(other.ankunftZeit)) {
			return false;
		}
		if (buchbar != other.buchbar) {
			return false;
		}
		if (flugNummer == null) {
			if (other.flugNummer != null) {
				return false;
			}
		} else if (!flugNummer.equals(other.flugNummer)) {
			return false;
		}
		if (flugzeug == null) {
			if (other.flugzeug != null) {
				return false;
			}
		} else if (!flugzeug.equals(other.flugzeug)) {
			return false;
		}
		if (preisSitzplatz != other.preisSitzplatz) {
			return false;
		}
		if (startFlughafen == null) {
			if (other.startFlughafen != null) {
				return false;
			}
		} else if (!startFlughafen.equals(other.startFlughafen)) {
			return false;
		}
		if (zaehlerGebuchteSitzplaetze != other.zaehlerGebuchteSitzplaetze) {
			return false;
		}
		if (Double.doubleToLongBits(zaehlerGepaeckGewicht) != Double.doubleToLongBits(other.zaehlerGepaeckGewicht)) {
			return false;
		}
		if (zielFlughafen == null) {
			if (other.zielFlughafen != null) {
				return false;
			}
		} else if (!zielFlughafen.equals(other.zielFlughafen)) {
			return false;
		}
		return true;
	}

}