package it.fallmerayer.codingGmbH.projektFlughafen.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlugTest {

	public static void main(String[] args) {
		try {
			String datumZeitMuster = "dd.MM.yyyy HH:mm:ss";        // Muster, dem das Datum entsprechen muss, damit es akzeptiert wird. M = Monat, d = Tag, y = Jahr, H = Stunde (von 0 bis 24), m = Minute, s = Sekunde.
			SimpleDateFormat format = new SimpleDateFormat(datumZeitMuster);    // Format wird in einem SimpleDateFormat-Objekt festgelegt.

			Date abflugZeit = format.parse("12.12.2017 13:05:10");           // Umwandlung des Strings in ein Datum, bei der eine Exception auftreten kann.
			Date ankunftZeit = format.parse("12.12.2017 17:35:50");          // Umwandlung des Strings in ein Datum, bei der eine Exception auftreten kann.

			Flug flug = new Flug(new Flugzeug("", 30, "Lufthansa", 100.0), "LH12345", new Flughafen("", "Muenchen", ""), new Flughafen("", "Barcelona", ""), 50, abflugZeit, ankunftZeit);

			System.out.println(" - Flug:");
			System.out.println("\t--> Flugzeug: " + flug.getFlugzeug().getAnzahlSitzplaetze() + " Sitzplaetze, Flug-Gesellschaft: " + flug.getFlugzeug().getFlugGesellschaft() + ", Gepaeckgewicht: " + flug.getFlugzeug().getGepaeckKapazitaet() + " kg.");
			System.out.println("\t--> Flugnummer: " + flug.getFlugNummer() + ".");
			System.out.println("\t--> Start-Flughafen: " + flug.getStartFlughafen().getStadt() + ".");
			System.out.println("\t--> Ziel-Flughafen: " + flug.getZielFlughafen().getStadt() + ".");
			System.out.println("\t--> Preis pro Sitzplatz: " + flug.getPreisSitzplatz() + " Euro.");
			System.out.println("\t--> Abflugs-Zeit: "+ flug.getAbflugZeit() + ".");
			System.out.println("\t--> Ankunfts-Zeit: "+ flug.getAnkunftZeit() + ".");
			System.out.println("\t--> Gebuchte Sitzplaetze: " + flug.getZaehlerGebuchteSitzplaetze() + " Sitzplaetze.");
			System.out.println("\t--> Gepaeck-Gewicht: " + flug.getZaehlerGepaeckGewicht() + " kg.");
			System.out.println("\t--> Buchbar: " + flug.isBuchbar() + ".");
			System.out.println("\t--> Dauer des Fluges: " + flug.getDauerFlug() + " Stunden.");
			System.out.println("\t--> Freie Plaetze: " + flug.getFreiePlaetze() + " Sitzplaetze.");
			System.out.println("\t--> Freies Geplaeckgewicht: " + flug.getFreiesGepaeckGewicht() + " kg.");
		} catch (ParseException e) {
			System.err.println("Die Zeichenkette kann leider nicht als gueltiges Datum interpretiert werden.");			// Eine Fehlermeldung wird auf die Standard-Fehlerausgabe geschrieben. In diesem Fall ist dies der Bildschirm. Eclipse gibt die Fehlermeldungen standardmaessig in roter Farbe aus.
		}
	}

}