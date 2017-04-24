package it.fallmerayer.codingGmbH.projektFlughafen.Modell;

import java.util.List;

/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public abstract class Buchungsprofil {

    private String flugNummer;
    private int buchungsID;
    private double gepaeckGewicht;

    //Dem Konstruktor müssen alle Attribute übergeben werden außer die "buchungsID", da diese von der Methode "BuchungsprofileSpeicher.getBuchungCounter()" übernommen werden muss.
    public Buchungsprofil(String flugNummer, double gepaeckGewicht) throws FlugNichtBuchbarException {
        this.flugNummer = flugNummer;
        this.buchungsID = BuchungsprofileSpeicher.getBuchungsCounter();          //Das "Buchungsprofil" erhält eine eindeutige "buchungsID" von der Klasse "BuchugsprofilSpeicher".
        BuchungsprofileSpeicher.setBuchungsCounter(BuchungsprofileSpeicher.getBuchungsCounter() + 1);     //Hier wird der "buchungsCounter" der Klasse "BuchungsprofileSpeicher" um 1 inkrementiert, damit die "buchungID" für jedes "Buchungsprofil" immer eindeutig bleibt.

        //Falls das "Flugzeug" buchbar ist noch genügend "gepäck" aufnehmen kann, wird das übergebene "gepäck" dem "zaehlerGepaeckGewicht" des "Fluges" hinzugefügt. Ansonsten kann der "Flug" nicht bebucht werden. Es wird eine "FlugNichtBucharExcepion" geworfen.
        if ((FluegeSpeicher.getInstance().getFlug(this.flugNummer).getFlugzeug().getGepaeckKapazitaet() - FluegeSpeicher.getInstance().getFlug(this.flugNummer).getZaehlerGepaeckGewicht()) >= gepaeckGewicht && FluegeSpeicher.getInstance().getFlug(this.flugNummer).isBuchbar()){
            FluegeSpeicher.getInstance().getFlug(this.flugNummer).setZaehlerGepaeckGewicht(FluegeSpeicher.getInstance().getFlug(this.flugNummer).getZaehlerGepaeckGewicht() + gepaeckGewicht);            //Hier wird das neue Gepäckgewicht des Fluges gesetzt.
            this.gepaeckGewicht = gepaeckGewicht;
        }else {
            throw new FlugNichtBuchbarException("Der Flug ist entweder nicht mehr buchbar oder das von Ihnen eingegebene Gepäck hat im Flugzeug nicht mehr Platz!");
        }
    }

    public Buchungsprofil(double gepaeckGewicht, int buchungsID){
        this.gepaeckGewicht = gepaeckGewicht;
        this.buchungsID = buchungsID;
    }

    public String getFlugNummer() {
        return flugNummer;
    }

    public void setFlugNummer(String flugNummer) {
        this.flugNummer = flugNummer;
    }

    public int getBuchungsID() {
        return buchungsID;
    }

    public void setBuchungsID(int buchungsID) {
        this.buchungsID = buchungsID;
    }

    public double getGepaeckGewicht() {
        return gepaeckGewicht;
    }

    public void setGepaeckGewicht(double gepaeckGewicht) throws FlugNichtBuchbarException{
        if ((FluegeSpeicher.getInstance().getFlug(this.flugNummer).getFlugzeug().getGepaeckKapazitaet() - FluegeSpeicher.getInstance().getFlug(this.flugNummer).getZaehlerGepaeckGewicht()) >= gepaeckGewicht && FluegeSpeicher.getInstance().getFlug(this.flugNummer).isBuchbar()){
            FluegeSpeicher.getInstance().getFlug(this.flugNummer).setZaehlerGepaeckGewicht(FluegeSpeicher.getInstance().getFlug(this.flugNummer).getZaehlerGepaeckGewicht() + gepaeckGewicht);            //Hier wird das neue Gepäckgewicht des Fluges gesetzt.
            this.gepaeckGewicht = gepaeckGewicht;
        }else {
            throw new FlugNichtBuchbarException("Der Flug ist entweder nicht mehr buchbar oder das von Ihnen eingegebene Gepäck hat im Flugzeug nicht mehr Platz!");
        }
    }

    public abstract Buchungsprofil erstelleAbgelaufeneBuchung(String flugNummer, double gepaeckGewicht, int PID, List<Mitflieger> personen, int buchungsID);

    public abstract double calculatePreis();
}
