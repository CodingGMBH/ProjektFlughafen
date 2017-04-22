package it.fallmerayer.codingGmbH.projektFlughafen.model;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Hannes Niederwolfsgruber on 11.04.2017.
 */
public class Angestellter extends Benutzerprofil {

    private static int angestellterCounter = 101;
    private static final int ANGESTELLTER_MAX = 1000;

    public Angestellter(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) throws TooMuchAngestellteException {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort);
        if(!(this instanceof Administrator)){
            if ((angestellterCounter + 1) <= ANGESTELLTER_MAX){
                this.setPID(angestellterCounter++);
            }else {
                throw new TooMuchAngestellteException();
            }
        }
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

    private BuchungsprofilAngestellter bucheFlugFuerKunde(String flugNummer, double gepaeckGewicht, List<Mitflieger> passagierListe) throws FlugNichtBuchbarException{
        BuchungsprofilAngestellter aktuellesBuchungsprofil = new BuchungsprofilAngestellter(flugNummer, gepaeckGewicht, this.getPID(), passagierListe);
        BuchungsprofileSpeicher.addBuchungsprofil(aktuellesBuchungsprofil);
        return aktuellesBuchungsprofil;
    }
}
