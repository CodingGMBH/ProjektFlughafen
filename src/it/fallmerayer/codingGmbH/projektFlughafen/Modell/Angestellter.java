package it.fallmerayer.codingGmbH.projektFlughafen.Modell;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Created by Hannes Niederwolfsgruber on 11.04.2017.
 */
public class Angestellter extends Benutzerprofil {

    private static int angestellterCounter = 101;
    private static final int ANGESTELLTER_MAX = 1000;

    //Wird ein "Angestellter" erstellt, so erhält er eine "PID" des Angestelltenkreises. Ruft jedoch ein "Administrator" in der super()-Methode den Konstruktor des "Angestellten" auf, so erhält er keine "PID" des ANgestelltenkreises.
    public Angestellter(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) throws TooMuchAngestellteException {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort);
        if(!(this instanceof Administrator)){
            if ((angestellterCounter + 1) <= ANGESTELLTER_MAX){
                this.setPID(angestellterCounter++);
            }else {
                throw new TooMuchAngestellteException("Es kann kein Angestellter mehr erstellt werden!");
            }
        }
    }

    //Wird ein "Angestellter" aus der Datei gelesen, so muss die "PID" übergeben werden.
    public Angestellter(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort, int PID) {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort, PID);
    }

    public static int getAngestellterCounter() {
        return angestellterCounter;
    }

    public static int getAngestellterMax() {
        return ANGESTELLTER_MAX;
    }

    public static void aktualisiereAngestellterCounter(int wert){
        angestellterCounter = wert;
    }

    //Es wird ein "BuchungsprofilAngestellter" erstellt und in die Liste des "BuchungsprofilSpeichers" eingefügt. Dabei muss der "Flug" "buchbar" sein und mit den gewählten Konfiguationen übereinstimmen. Andernfalls wird im "Buchungsprofil" eine "FlugNichtBuchbarException" geworfen.
    public BuchungsprofilAngestellter bucheFlugFuerKunde(String flugNummer, double gepaeckGewicht, List<Mitflieger> passagierListe) throws FlugNichtBuchbarException{
        BuchungsprofilAngestellter aktuellesBuchungsprofil = new BuchungsprofilAngestellter(flugNummer, gepaeckGewicht, this.getPID(), passagierListe);
        BuchungsprofileSpeicher.getInstance().addBuchungsprofil(aktuellesBuchungsprofil);
        return aktuellesBuchungsprofil;
    }
}
