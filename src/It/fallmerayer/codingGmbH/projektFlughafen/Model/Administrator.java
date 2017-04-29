package It.fallmerayer.codingGmbH.projektFlughafen.Model;
import java.time.LocalDateTime;
/**
 * Created by Hannes Niederwolfsgruber on 11.04.2017.
 */
public class Administrator extends Angestellter {

    private static int administratorCounter = 1;
    private static final int ADMINISTRATOR_MAX = 100;

    //Es kann nur ein "Administrator" erstellt werden, falls noch nicht das Maximum an "Administratoren" erreicht wurde.
    public Administrator(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) throws TooMuchAngestellteException {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort);
        if ((administratorCounter + 1) <= ADMINISTRATOR_MAX){
            this.setPID(administratorCounter++);
        }else {
            throw new TooMuchAngestellteException("Es kann kein Administrator mehr erstellt werden!");
        }
    }

    //Wird ein "Administrator" aus der Datei gelesen, so muss die PID übergeben werden.
    public Administrator(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort, int PID) {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort, PID);
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
        BenutzerprofilSpeicher.getInstance().addBenutzerprofil(new Angestellter(vorname, nachname, email, geburtsDatum, benutzerName, passwort));
    }

    public void erstelleAdministratorKonto(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) throws TooMuchAngestellteException{
        BenutzerprofilSpeicher.getInstance().addBenutzerprofil(new Administrator(vorname, nachname, email, geburtsDatum, benutzerName, passwort));
    }
}
