package it.fallmerayer.codingGmbH.projektFlughafen.model;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class BuchungsprofilAngestellter extends Buchungsprofil {		//Klassendiagramm konform.

    private int angestellterPID;
    private List<Mitflieger> passagierListe = new LinkedList<>();

    //Man kann eine Buchung ohne Inhalt erstellen.
    public BuchungsprofilAngestellter(){}

    //Man kann eine Buchung mit "mitfliegerListe" erstellen, falls diese schon bekannt ist.
    public BuchungsprofilAngestellter(String flugNummer, double gepaeckGewicht, int angestellterPID, List<Mitflieger> passagierListe) {
        super(flugNummer, gepaeckGewicht);
        this.angestellterPID = angestellterPID;
        this.passagierListe = passagierListe;
        FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + passagierListe.size());
    }

    //Man kann ein BuchungsprofilAngestellter ohne "mitfliegerListe" erstellen.
    public BuchungsprofilAngestellter(String flugNummer, double gepaeckGewicht, int angestellterPID) {
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

    public void setPassagierListe(List<Mitflieger> passagierListe) {
        this.passagierListe = passagierListe;
        FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + passagierListe.size());
    }

    @Override
    public double calculatePreis() {
        return (FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getPreisSitzplatz() + this.getGepaeckGewicht() * this.passagierListe.size()) + 50;
    }

    public void addPassagier(Mitflieger passagier){
        this.passagierListe.add(passagier);
        FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + 1);
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
