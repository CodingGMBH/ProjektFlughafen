package it.fallmerayer.codingGmbH.projektFlughafen.Modell;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class BuchungsprofilAngestellter extends Buchungsprofil {

    private int angestellterPID;
    private List<Mitflieger> passagierListe = new LinkedList<>();

    //Man kann eine Buchung mit "mitfliegerListe" erstellen, falls diese schon bekannt ist.
    public BuchungsprofilAngestellter(String flugNummer, double gepaeckGewicht, int angestellterPID, List<Mitflieger> passagierListe) throws FlugNichtBuchbarException {
        super(flugNummer, gepaeckGewicht);
        this.angestellterPID = angestellterPID;

        //Es kann nur ein "BuchungsprofilAngestellter" erstellt werden, falls alle "passagiere" noch Platz im "Flugzeug" haben.
        if ((FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze()) >= passagierListe.size()){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + passagierListe.size());
            this.passagierListe = passagierListe;
        }else {
            throw new FlugNichtBuchbarException("Der Flug besitzt nich mehr genügend Sitzplätze, damit alle von Ihnen eingegebenen Passagiere mitfliegen können!");
        }
    }

    //Man kann ein BuchungsprofilAngestellter ohne "mitfliegerListe" erstellen.
    public BuchungsprofilAngestellter(String flugNummer, double gepaeckGewicht, int angestellterPID) throws FlugNichtBuchbarException {
        super(flugNummer, gepaeckGewicht);
        this.angestellterPID = angestellterPID;
    }

    //Mit diesem package private Konstruktor erstellt man ein abgelaufenes Buchungsprofil.
    BuchungsprofilAngestellter(String flugNummer, double gepaeckGewicht, int angestellterPID, List<Mitflieger> passagierListe, int buchungsID){
        super(flugNummer, gepaeckGewicht, buchungsID);
        this.angestellterPID = angestellterPID;
        this.passagierListe = passagierListe;
    }
    
    public int getAngestellterPID() {
        return angestellterPID;
    }

    public void setAngestellterPID(int angestellterPID) {
        this.angestellterPID = angestellterPID;
    }

    public List<Mitflieger> getPassagierListe() {
        return passagierListe;
    }

    //Es können nur "passagiere" eingefügt werden, falls noch genügend Platz im Flugzeug frei ist.
    public void setPassagierListe(List<Mitflieger> passagierListe) throws FlugNichtBuchbarException {
        if ((FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze()) >= passagierListe.size()){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + passagierListe.size());
            this.passagierListe = passagierListe;
        }else {
            throw new FlugNichtBuchbarException("Der Flug besitzt nich mehr genügend Sitzplätze, damit alle von Ihnen eingegebenen Passagiere mitfliegen können!");
        }
    }

    @Override
    public double calculatePreis() {
        return (FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getPreisSitzplatz() + this.getGepaeckGewicht() * this.passagierListe.size()) + 50;
    }
    
    //Es kann nur ein "passagier" hinzugefügt werden, falls noch genügend Platz im Flugzeug frei ist.
    public void addPassagier(Mitflieger passagier) throws FlugNichtBuchbarException{
        if ((FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze()) >= 1){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + 1);
            this.passagierListe.add(passagier);
        }else {
            throw new FlugNichtBuchbarException("Der Flug besitzt nich mehr genügend Sitzplätze, damit alle von Ihnen eingegebenen Passagiere mitfliegen können!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuchungsprofilAngestellter)) return false;

        BuchungsprofilAngestellter that = (BuchungsprofilAngestellter) o;

        if (getAngestellterPID() != that.getAngestellterPID()) return false;
        return getPassagierListe() != null ? getPassagierListe().equals(that.getPassagierListe()) : that.getPassagierListe() == null;
    }

    @Override
    public int hashCode() {
        int result = getAngestellterPID();
        result = 31 * result + (getPassagierListe() != null ? getPassagierListe().hashCode() : 0);
        return result;
    }
}
