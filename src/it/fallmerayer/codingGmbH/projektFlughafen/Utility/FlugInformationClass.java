package it.fallmerayer.codingGmbH.projektFlughafen.Utility;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.FluegeSpeicher;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Flug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gabriel on 26.04.17.
 */
public class FlugInformationClass {
    String startOrt;
    String zielOrt;
    String startZeit;
    String ankunftsZeit;
    String datum;
    double preis;
    double gepaeck;
    int personenAnzahl;
    String flugId;
    String flugGesellschaft;


    public String getStartOrt() {
        return startOrt;
    }

    public void setStartOrt(String startOrt) {
        this.startOrt = startOrt;
    }

    public String getZielOrt() {
        return zielOrt;
    }

    public void setZielOrt(String zielOrt) {
        this.zielOrt = zielOrt;
    }

    public String getStartZeit() {
        return startZeit;
    }

    public void setStartZeit(String startZeit) {
        this.startZeit = startZeit;
    }

    public String getAnkunftsZeit() {
        return ankunftsZeit;
    }

    public void setAnkunftsZeit(String ankunftsZeit) {
        this.ankunftsZeit = ankunftsZeit;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getGepaeck() {
        return gepaeck;
    }

    public void setGepaeck(double gepaeck) {
        this.gepaeck = gepaeck;
    }

    public int getPersonenAnzahl() {
        return personenAnzahl;
    }

    public void setPersonenAnzahl(int personenAnzahl) {
        this.personenAnzahl = personenAnzahl;
    }

    public String getFlugId() {
        return flugId;
    }

    public void setFlugId(String flugId) {
        this.flugId = flugId;
    }

    public String getFlugGesellschaft() {
        return flugGesellschaft;
    }

    public void setFlugGesellschaft(String flugGesellschaft) {
        this.flugGesellschaft = flugGesellschaft;
    }

    public double calulatePreis(){
        return (preis + gepaeck * personenAnzahl) + 50;
    }

    public FlugInformationClass(String startOrt, String zielOrt, String startZeit, String ankunftsZeit, String datum, double preis, double gepaeck, int personenAnzahl) {
        this.startOrt = startOrt;
        this.zielOrt = zielOrt;
        this.startZeit = startZeit;
        this.ankunftsZeit = ankunftsZeit;
        this.datum = datum;
        this.preis = preis;
        this.gepaeck = gepaeck;
        this.personenAnzahl = personenAnzahl;
    }

    public FlugInformationClass(String flugId, String flugGesellschaft, String startOrt, String zielOrt, String startZeit, String ankunftsZeit, String datum, double preis, double gepaeck, int personenAnzahl) {
        this.flugId = flugId;
        this.flugGesellschaft = flugGesellschaft;
        this.startOrt = startOrt;
        this.zielOrt = zielOrt;
        this.startZeit = startZeit;
        this.ankunftsZeit = ankunftsZeit;
        this.datum = datum;
        this.preis = preis;
        this.gepaeck = gepaeck;
        this.personenAnzahl = personenAnzahl;
    }

    public static ObservableList<FlugInformationClass> getObjektList(List<Flug> flugList) {
        LinkedList<FlugInformationClass> flugInformationObservableList = new LinkedList<>();

        for (Flug flug : flugList) {
            flugInformationObservableList.add(new FlugInformationClass(flug.getFlugNummer(), flug.getFlugzeug().getFlugGesellschaft(), flug.getStartFlughafen().getStadt(), flug.getZielFlughafen().getStadt(), flug.getAbflugZeit().format(DateTimeFormatter.ofPattern("HH:mm")), flug.getAnkunftZeit().format(DateTimeFormatter.ofPattern("HH:mm")), flug.getAbflugZeit().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), flug.getPreisSitzplatz(), 0.0, 0));
        }

        return FXCollections.observableList(flugInformationObservableList);
    }
}
