package it.fallmerayer.codingGmbH.projektFlughafen.model;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class BuchungsprofilAnwender extends Buchungsprofil {        //Klassendiagramm konform.

    private int anwenderPID;
    private List<Mitflieger> mitfliegerListe = new LinkedList<>();

    //Eine Buchung ohne Inhalt kann erstellt werden.
    public BuchungsprofilAnwender() {}

    //Es kann eine Buchung mit "mitfliegerListe" erstellt werden, falls die "mitfliegerListe" schon bekannt ist.
    public BuchungsprofilAnwender(String flugNummer, double gepaeckGewicht, int anwenderPID, List<Mitflieger> mitfliegerListe) {
        super(flugNummer, gepaeckGewicht);
        this.anwenderPID = anwenderPID;
        this.mitfliegerListe = mitfliegerListe;
        if(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).!!!!!!)
        FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + mitfliegerListe.size());
    }

    //Es kann aber auch eine Buchung erstellt werden, bei der keine "mitfligerListe" übergeben wird. Mitflieger können später hinzugefügt werden.
    public BuchungsprofilAnwender(String flugNummer, double gepaeckGewicht, int anwenderPID) {
        super(flugNummer, gepaeckGewicht);
        this.anwenderPID = anwenderPID;
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

    public void setMitfliegerListe(List<Mitflieger> mitfliegerListe) {
        this.mitfliegerListe = mitfliegerListe;
        FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + mitfliegerListe.size());
    }

    @Override
    public double calculatePreis() {
        return (FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getPreisSitzplatz() + this.getGepaeckGewicht() * this.mitfliegerListe.size()) + 50;
    }

    public void addMitflieger(Mitflieger mitflieger){
        this.mitfliegerListe.add(mitflieger);
        FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + 1);
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
