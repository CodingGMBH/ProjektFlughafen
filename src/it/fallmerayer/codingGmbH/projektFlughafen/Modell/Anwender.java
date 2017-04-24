package it.fallmerayer.codingGmbH.projektFlughafen.Modell;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class Anwender extends Benutzerprofil {

    private int identitaetsNummer;
    private List<WunschlistenEintrag> wunschliste;
    private static int anwenderCounter = 1001;

    //Dem Konstruktor wird keine "wunschliste" übergeben, da erst später ein "WunschlistenEintrag" der "wunschliste" hinzugefügt werden kann. Die "PID" des "Anwenders" wird anschließend gesetzt.
    public Anwender(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort, int identitaetsNummer) {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort);
        this.setPID(anwenderCounter++);
        this.identitaetsNummer = identitaetsNummer;
        this.wunschliste = new LinkedList<>();
    }

    //Falls ein "Anwender" aus der Datei gelesen wird, muss eine "PID" übergeben werden.
    public Anwender(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort, int PID, int identitaetsNummer) {
        super(vorname, nachname, email, geburtsDatum, benutzerName, passwort, PID);
        this.identitaetsNummer = identitaetsNummer;
        this.wunschliste = new LinkedList<>();
    }

    public int getIdentitaetsNummer() {
        return identitaetsNummer;
    }

    public void setIdentitaetsNummer(int identitaetsNummer) {
        this.identitaetsNummer = identitaetsNummer;
    }

    public List<WunschlistenEintrag> getWunschliste() {
        return wunschliste;
    }

    public void setWunschliste(List<WunschlistenEintrag> wunschliste) {
        this.wunschliste = wunschliste;
    }

    public static int getAnwenderCounter() {
        return anwenderCounter;
    }

    //Der übergebene Wert ist die "PID" für den nächsten Anwender.
    public static void aktualisiereAnwenderCounter(int wert){
        anwenderCounter = wert;
    }

    //Ein "BuchungsprofilAnwender" wird erstellt. "PID" wird übergeben und die Buchung erhält automatisch eine "buchungsID". Dann wird die Buchung dem BuchungsprofilSpeicher übergeben. Es wird eine Exception geworfen, falls der Flug nicht buchbar ist.
    public BuchungsprofilAnwender bucheFlug(String flugNummer, double gepaeckGewicht, List<Mitflieger> mitfliegerListe) throws FlugNichtBuchbarException{
        BuchungsprofilAnwender aktuellesBuchungsprofil = new BuchungsprofilAnwender(flugNummer, gepaeckGewicht, this.getPID(), mitfliegerListe);
        BuchungsprofileSpeicher.getInstance().addBuchungsprofil(aktuellesBuchungsprofil);
        return aktuellesBuchungsprofil;
    }

    //Ein "Flug" kann in die "wunschliste" gelegt werden, aber nur, falls der "Flug" zu diesem Zeitpunkt "buchbar" ist und mit den gewünschten Konfigurationen übereinstimmt. Wird der "Flug" hineingelegt, so wird der WunschlistenEintrag auf "true" gesetzt.
    public boolean legeFlugInWunschliste(String flugNummer, int anzahlPassagiere, double gepaeckGewicht){
        Flug flugFuerWunschliste = FluegeSpeicher.getInstance().getFlug(flugNummer);

        if (flugFuerWunschliste.isBuchbar() && (flugFuerWunschliste.getFlugzeug().getAnzahlSitzplaetze() - flugFuerWunschliste.getZaehlerGebuchteSitzplaetze()) >= anzahlPassagiere && ((flugFuerWunschliste.getFlugzeug().gepaeckKapazitaet() - flugFuerWunschliste.getZaehlerGepaeckGewicht())) >= gepaeckGewicht){
            this.wunschliste.add(new WunschlistenEintrag(flugNummer, anzahlPassagiere, gepaeckGewicht, true));
            return true;
        }else{
            return false;
        }
    }

    //Es wird der zur "flugnummer" übergebene "Flug" aus der "wunschliste" entfernt.
    public boolean entferneFlugVonWunschliste(String flugNummer){
        for (WunschlistenEintrag wunschlistenEintrag:wunschliste) {
            if (wunschlistenEintrag.getFlugNummer().equals(flugNummer)){
                return wunschliste.remove(wunschlistenEintrag);
            }
        }
        return false;
    }

    //Der "Flug" muss buchbar und mit der Konfiguration übereinstimmen, damit er noch länger in der "wunschliste" "buchbar" sein kann.
    public void aktualisiereWunschliste(){
        Flug aktuellerFlug;

        for (WunschlistenEintrag flugInWunschliste: this.wunschliste) {

            //Das ist der "Flug", dessen "WunschlistenEintrag" in der "wunschliste" ist. Die dazugehörige "flugnummer" wird vom "FluegeSpeicher" geholt, um zu überprüfen, ob der "Flug" in der "wunschliste" noch "buchbar" ist.
            aktuellerFlug = FluegeSpeicher.getInstance().getFlug(flugInWunschliste.getFlugNummer());

            if (aktuellerFlug.isBuchbar() && (aktuellerFlug.getFlugzeug().getAnzahlSitzplaetze() - aktuellerFlug.getZaehlerGebuchteSitzplaetze()) >= flugInWunschliste.getAnzahlPassagiere() && ((aktuellerFlug.getFlugzeug().gepaeckKapazitaet() - aktuellerFlug.getZaehlerGepaeckGewicht())) >= flugInWunschliste.getGepaeckGewicht()){
                flugInWunschliste.setBuchbar(true);
            }else {
                flugInWunschliste.setBuchbar(false);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anwender)) return false;
        if (!super.equals(o)) return false;

        Anwender anwender = (Anwender) o;

        if (getIdentitaetsNummer() != anwender.getIdentitaetsNummer()) return false;
        return getWunschliste() != null ? getWunschliste().equals(anwender.getWunschliste()) : anwender.getWunschliste() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getIdentitaetsNummer();
        result = 31 * result + (getWunschliste() != null ? getWunschliste().hashCode() : 0);
        return result;
    }
}
