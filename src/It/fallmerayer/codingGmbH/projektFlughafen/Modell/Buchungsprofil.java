package It.fallmerayer.codingGmbH.projektFlughafen.Modell;

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
        setGepaeckGewicht(gepaeckGewicht);
        FluegeSpeicher.getInstance().aktualiesereBuchbar();
    }

    Buchungsprofil(String flugNummer, double gepaeckGewicht, int buchungsID){
        this.gepaeckGewicht = gepaeckGewicht;
        this.flugNummer = flugNummer;
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
        FluegeSpeicher.getInstance().aktualiesereBuchbar();
    }
    
    public abstract double calculatePreis();
}
