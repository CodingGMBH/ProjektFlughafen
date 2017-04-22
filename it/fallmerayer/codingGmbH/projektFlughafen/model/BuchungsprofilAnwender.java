package it.fallmerayer.codingGmbH.projektFlughafen.model;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class BuchungsprofilAnwender extends Buchungsprofil {

    private int anwenderPID;
    private List<Mitflieger> mitfliegerListe = new LinkedList<>();

    //Eine Buchung ohne Inhalt kann erstellt werden.
    public BuchungsprofilAnwender() {}

    //Es kann eine Buchung mit "mitfliegerListe" erstellt werden, falls die "mitfliegerListe" schon bekannt ist.
    public BuchungsprofilAnwender(String flugNummer, double gepaeckGewicht, int anwenderPID, List<Mitflieger> mitfliegerListe) throws FlugNichtBuchbarException{
        super(flugNummer, gepaeckGewicht);
        this.anwenderPID = anwenderPID;        
        if ((FluegeSpeicher.getInstance.getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getGebuchteSitzplaetze()) >= mitfliegerListe.size()){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + mitfliegerListe.size());
            this.mitfliegerListe = mitfliegerListe;
        }else {
            throw new FlugNichtBuchbarException();
        }
    }

    //Es kann aber auch eine Buchung erstellt werden, bei der keine "mitfligerListe" übergeben wird. Mitflieger können später hinzugefügt werden.
    public BuchungsprofilAnwender(String flugNummer, double gepaeckGewicht, int anwenderPID) throws FlugNichtBuchbarException{
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

    //Es können nur Mitflieger eingefügt werden, wenn im Flugzeug noch genügend Platz ist.
    public void setMitfliegerListe(List<Mitflieger> mitfliegerListe) throws FlugNichtBuchbarException{
        if ((FluegeSpeicher.getInstance.getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getGebuchteSitzplaetze()) >= mitfliegerListe.size()){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + mitfliegerListe.size());
            this.mitfliegerListe = mitfliegerListe;
        }else {
            throw new FlugNichtBuchbarException();
        }
    }

    @Override
    public double calculatePreis() {
        return (FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getPreisSitzplatz() + this.getGepaeckGewicht() * this.mitfliegerListe.size()) + 50;
    }

     //Es kann nur ein Mitflieger geaddet werden, fall noch ein Platz im Flugzeug frei ist.
    public void addMitflieger(Mitflieger mitflieger) thrwos FlugNichtBuchbarException{
        if ((FluegeSpeicher.getInstance.getFlug(this.getFlugNummer()).getFlugzeug().getAnzahlSitzplaetze() - FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getGebuchteSitzplaetze()) >= 1){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGebuchteSitzplaetze(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGebuchteSitzplaetze() + 1);
            this.mitfliegerListe.add(mitflieger);
        }else {
            throw new FlugNichtBuchbarException();
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
