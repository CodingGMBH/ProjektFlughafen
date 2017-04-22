package it.fallmerayer.codingGmbH.projektFlughafen.model;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public abstract class Buchungsprofil {      //Klassendiagramm konform.

    private String flugNummer;
    private int buchungsID;
    private double gepaeckGewicht;


    //Es kann ein Buchungsprofil ohne übergebene Attribute erstellt werden, die Attribute können später gestetzt werden.
    public Buchungsprofil(){}

    //Dem Konstruktor müssen alle Attribute übergeben werden außer die buchungsID, da diese von der Methode BuchungsprofilSpeicher.getBuchungCounter() übernommen werden muss.
    public Buchungsprofil(String flugNummer, double gepaeckGewicht) throws FlugNichtBuchbarException {
        this.flugNummer = flugNummer;
        this.buchungsID = BuchungsprofilSpeicher.getBuchungsCounter();          //Die Buchung erhält eine eindeutige BuchungsID von der Klasse BuchugsprofilSpeicher.
        BuchungsprofilSpeicher.getInstance().setBuchungsCounter(BuchungsprofilSpeicher.getBuchungsCounter() + 1);     //Hier wird der buchungsCounter der Klasse BuchungsprofilSpeicher um 1 inkrementiert, damit die BuchungID für jede Buchung eindeutig bleibt.
        this.gepaeckGewicht = gepaeckGewicht;
        if ((FlueSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getGepaeckKapazitaet() - FluegeSpeicher.getInstance().getFlug(this.flugNummer).getZaehlerGepaeckgewicht()) >= gepaeckGewicht){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGepaeckGewicht(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGepaeckGewicht() + gepaeckGewicht);            //Hier wird das neue Gepäckgewicht des Fluges gesetzt.
        }else {
            throw new FlugNichtBuchbarException();
        }
    }
    //Im Folgenden können die Attribute ausgelesen oder neu gesetzt werden.

    public String getFlugNummer() {
        return flugNummer;
    }

    public void setFlugNummer(String flugNummer) {
        this.flugNummer = flugNummer;
    }

    public int getBuchungsID() {
        return buchungsID;
    }

    public double getGepaeckGewicht() {
        return gepaeckGewicht;
    }

    public void setGepaeckGewicht(double gepaeckGewicht) throws FlugNichtBuchbarException {
        this.gepaeckGewicht = gepaeckGewicht;
        if ((FlueSpeicher.getInstance().getFlug(this.getFlugNummer()).getFlugzeug().getGepaeckKapazitaet() - FluegeSpeicher.getInstance().getFlug(this.flugNummer).getZaehlerGepaeckgewicht()) >= gepaeckGewicht){
            FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).setZaehlerGepaeckGewicht(FluegeSpeicher.getInstance().getFlug(this.getFlugNummer()).getZaehlerGepaeckGewicht() + gepaeckGewicht);            //Hier wird das neue Gepäckgewicht des Fluges gesetzt.
        }else {
            throw new FlugNichtBuchbarException();
        }
    }

    public abstract double calculatePreis();
}
