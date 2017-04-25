package it.fallmerayer.codingGmbH.projektFlughafen.Modell;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class BuchungsprofilAnwender extends Buchungsprofil {

    private int anwenderPID;
    private List<Mitflieger> mitfliegerListe = new LinkedList<>();

    //Es kann eine Buchung mit "mitfliegerListe" erstellt werden, falls die "mitfliegerListe" schon bekannt ist.
    public BuchungsprofilAnwender(String flugNummer, double gepaeckGewicht, int anwenderPID, List<Mitflieger> mitfliegerListe) throws FlugNichtBuchbarException{
        super(flugNummer, gepaeckGewicht);
        this.anwenderPID = anwenderPID;

        //Es kann nur ein "BuchungsprofilAnwender" erstellt werden, falls alle "Mitflieger" noch Platz im "Flugzeug" haben.
        if ((FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze()) >= (mitfliegerListe.size() + 1)){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + (mitfliegerListe.size() + 1));
            this.mitfliegerListe = mitfliegerListe;
        }else {
            throw new FlugNichtBuchbarException("Der Flug besitzt nich mehr genügend Sitzplätze, damit alle von Ihnen eingegebenen Mitflieger mitfliegen können!");
        }
    }

    //Es kann aber auch eine Buchung erstellt werden, bei der keine "mitfligerListe" übergeben wird. "mitflieger" können später hinzugefügt werden.
    public BuchungsprofilAnwender(String flugNummer, double gepaeckGewicht, int anwenderPID) throws FlugNichtBuchbarException{
        super(flugNummer, gepaeckGewicht);
        this.anwenderPID = anwenderPID;
    }

    //Mit diesem package private Konstruktor erstellt man ein abgelaufenes Buchungsprofil.
    BuchungsprofilAnwender(String flugNummer, double gepaeckGewicht, int anwenderPID, List<Mitflieger> mitfliegerListe, int buchungsID){
        super(flugNummer, gepaeckGewicht, buchungsID);
        this.anwenderPID = anwenderPID;
        this.mitfliegerListe = mitfliegerListe;
    }

    public int getAnwenderPID() {
        return anwenderPID;
    }

    public void setAnwenderPID(int anwenderPID) {
        this.anwenderPID = anwenderPID;
    }

    public List<Mitflieger> getMitfliegerListe() {
        return mitfliegerListe;
    }

    //Es können nur Mitflieger eingefügt werden, wenn im Flugzeug noch genügend Platz ist.
    public void setMitfliegerListe(List<Mitflieger> mitfliegerListe) throws FlugNichtBuchbarException{
        if ((FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze()) >= (mitfliegerListe.size() + 1)){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + (mitfliegerListe.size() + 1));
            this.mitfliegerListe = mitfliegerListe;
        }else {
            throw new FlugNichtBuchbarException("Der Flug besitzt nich mehr genügend Sitzplätze, damit alle von Ihnen eingegebenen Mitflieger mitfliegen können!");
        }
    }

    @Override
    public double calculatePreis() {
        return (FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getPreisSitzplatz() + this.getGepaeckGewicht() * this.mitfliegerListe.size()) + 50;
    }

    //Es kann nur ein Mitflieger geaddet werden, falls noch ein Platz im Flugzeug frei ist.
    public void addMitflieger(Mitflieger mitflieger) throws FlugNichtBuchbarException{
        if ((FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze()) >= 1){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + 1);
            this.mitfliegerListe.add(mitflieger);
        }else {
            throw new FlugNichtBuchbarException("Es konnte kein Mitflieger mehr hinzugefügt werden, da kein Platz mehr im Flieger buchbar ist!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuchungsprofilAnwender)) return false;

        BuchungsprofilAnwender that = (BuchungsprofilAnwender) o;

        if (getAnwenderPID() != that.getAnwenderPID()) return false;
        return getMitfliegerListe() != null ? getMitfliegerListe().equals(that.getMitfliegerListe()) : that.getMitfliegerListe() == null;
    }

    @Override
    public int hashCode() {
        int result = getAnwenderPID();
        result = 31 * result + (getMitfliegerListe() != null ? getMitfliegerListe().hashCode() : 0);
        return result;
    }
}
