package it.fallmerayer.codingGmbH.projektFlughafen.model;
import java.time.LocalDateTime;
/**
 * Created by Hannes Niederwolfsgruber on 11.04.2017.
 */
public class Administrator extends Angestellter {

    private static int administratorCounter = 1;
    private static final int ADMINISTRATOR_MAX = 100;

    public Administrator(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) throws TooMuchAngestellteException {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort);
        if ((administratorCounter + 1) <= ADMINISTRATOR_MAX){
            this.setPID(administratorCounter++);
        }else {
            throw new TooMuchAngestellteException();
        }
    }

    public static int getAdministratorCounter() {
        return administratorCounter;
    }

    public static int getAdministratorMax() {
        return ADMINISTRATOR_MAX;
    }

    public static void aktualisiereAdministratorCounter(int wert){
        administratorCounter = wert;
    }

    public void erstelleAngestelltenKonto(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) throws TooMuchAngestellteException{
        Angestellter aktuellerAngestellter = new Angestellter(vorname, nachname, email, geburtsDatum, benutzerName, passwort);
        BenutzerprofilSpeicher.getInstance().addBenutzerprofil(aktuellerAngestellter);
    }

    public void erstelleAdministratorKonto(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) throws TooMuchAngestellteException{
        Administrator aktuellerAdministrator = new Administrator(vorname, nachname, email, geburtsDatum, benutzerName, passwort);
        BenutzerprofilSpeicher.getInstance().addBenutzerprofil(aktuellerAdministrator);
    }
}
