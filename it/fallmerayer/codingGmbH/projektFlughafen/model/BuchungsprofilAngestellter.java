package it.fallmerayer.codingGmbH.projektFlughafen.model;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class BuchungsprofilAngestellter extends Buchungsprofil {

    private int angestellterPID;
    private List<Mitflieger> passagierListe = new LinkedList<>();

    //Man kann eine Buchung ohne Inhalt erstellen.
    public BuchungsprofilAngestellter(){}

    //Man kann eine Buchung mit "mitfliegerListe" erstellen, falls diese schon bekannt ist.
    public BuchungsprofilAngestellter(String flugNummer, double gepaeckGewicht, int angestellterPID, List<Mitflieger> passagierListe) throws FlugNichtBuchbarException {
        super(flugNummer, gepaeckGewicht);
        this.angestellterPID = angestellterPID;
        if ((FluegeSpeicher.getInstance.getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getGebuchteSitzplaetze()) >= passagierListe.size()){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + passagierListe.size());
            this.passagierListe = passagierListe;
        }else {
            throw new FlugNichtBuchbarException();
        }
    }

    //Man kann ein BuchungsprofilAngestellter ohne "mitfliegerListe" erstellen.
    public BuchungsprofilAngestellter(String flugNummer, double gepaeckGewicht, int angestellterPID) throws FlugNichtBuchbarException {
        super(flugNummer, gepaeckGewicht);
        this.angestellterPID = angestellterPID;
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
        if ((FluegeSpeicher.getInstance.getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getGebuchteSitzplaetze()) >= passagierListe.size()){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + passagierListe.size());
            this.passagierListe = passagierListe;
        }else {
            throw new FlugNichtBuchbarException();
        }
    }

    @Override
    public double calculatePreis() {
        return (FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getPreisSitzplatz() + this.getGepaeckGewicht() * this.passagierListe.size()) + 50;
    }

    //Es kann nur ein "passagier" hinzugefügt werden, falls noch genügend Platz im Flugzeug frei ist.
    public void addPassagier(Mitflieger passagier) throws FlugNichtBuchbarException{
        if ((FluegeSpeicher.getInstance.getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getGebuchteSitzplaetze()) >= 1){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + 1);
            this.passagierListe.add(passagier);
        }else {
            throw new FlugNichtBuchbarException();
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
