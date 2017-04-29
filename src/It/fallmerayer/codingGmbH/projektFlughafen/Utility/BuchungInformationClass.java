package It.fallmerayer.codingGmbH.projektFlughafen.Utility;

import It.fallmerayer.codingGmbH.projektFlughafen.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gabriel on 26.04.17.
 */
public class BuchungInformationClass {
    String startOrt;
    String zielOrt;
    String startZeit;
    String ankunftsZeit;
    String datum;
    double preis;
    double gepaeck;
    int personenAnzahl;
    String gebuchtVon;
    String gebuchtFuer;

    int buchungsID;

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

    public String getGebuchtVon() {
        return gebuchtVon;
    }

    public void setGebuchtVon(String gebuchtVon) {
        this.gebuchtVon = gebuchtVon;
    }

    public String getGebuchtFuer() {
        return gebuchtFuer;
    }

    public void setGebuchtFuer(String gebuchtFuer) {
        this.gebuchtFuer = gebuchtFuer;
    }

    public int getBuchungsID() {
        return buchungsID;
    }

    public void setBuchungsID(int buchungsID) {
        this.buchungsID = buchungsID;
    }

    public BuchungInformationClass(int buchungsID, String startOrt, String zielOrt, String startZeit, String ankunftsZeit, String datum, double preis, double gepaeck, int personenAnzahl, String gebuchtVon, String gebuchtFuer) {

        this.buchungsID = buchungsID;
        this.startOrt = startOrt;
        this.zielOrt = zielOrt;
        this.startZeit = startZeit;
        this.ankunftsZeit = ankunftsZeit;
        this.datum = datum;
        this.preis = preis;
        this.gepaeck = gepaeck;
        this.personenAnzahl = personenAnzahl;
        this.gebuchtVon = gebuchtVon;
        this.gebuchtFuer = gebuchtFuer;

    }

    public BuchungInformationClass(int buchungsID, String startOrt, String zielOrt, String startZeit, String ankunftsZeit, String datum, double preis, double gepaeck, int personenAnzahl, String gebuchtFuer) {

        this.buchungsID = buchungsID;
        this.startOrt = startOrt;
        this.zielOrt = zielOrt;
        this.startZeit = startZeit;
        this.ankunftsZeit = ankunftsZeit;
        this.datum = datum;
        this.preis = preis;
        this.gepaeck = gepaeck;
        this.personenAnzahl = personenAnzahl;
        this.gebuchtFuer = gebuchtFuer;

    }

    public BuchungInformationClass(int buchungsID, String startOrt, String zielOrt, String startZeit, String ankunftsZeit, String datum, double preis, double gepaeck, int personenAnzahl) {

        this.buchungsID = buchungsID;
        this.startOrt = startOrt;
        this.zielOrt = zielOrt;
        this.startZeit = startZeit;
        this.ankunftsZeit = ankunftsZeit;
        this.datum = datum;
        this.preis = preis;
        this.gepaeck = gepaeck;
        this.personenAnzahl = personenAnzahl;

    }

    public static ObservableList<BuchungInformationClass> getObjektListAdmin(List<Buchungsprofil> buchungsprofilList) {
        LinkedList<BuchungInformationClass> buchungInformationClasseList = new LinkedList<>();

        for (Buchungsprofil buchungsprofiel : buchungsprofilList) {
            Flug neuerFlug = FluegeSpeicher.getInstance().getFlug(buchungsprofiel.getFlugNummer());
            if (buchungsprofiel instanceof BuchungsprofilAngestellter){
                buchungInformationClasseList.add(new BuchungInformationClass(buchungsprofiel.getBuchungsID(), neuerFlug.getStartFlughafen().getStadt(), neuerFlug.getZielFlughafen().getStadt(), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAnkunftZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), buchungsprofiel.calculatePreis(), buchungsprofiel.getGepaeckGewicht(), (((BuchungsprofilAngestellter) buchungsprofiel).getPassagierListe().size() + 1), BenutzerprofilSpeicher.getInstance().getBenutzerprofil(((BuchungsprofilAngestellter) buchungsprofiel).getAngestellterPID()).getBenutzerName(), (((BuchungsprofilAngestellter) buchungsprofiel).getPassagierListe().get(0).getVorname())));
            }else if (buchungsprofiel instanceof BuchungsprofilAnwender){
                buchungInformationClasseList.add(new BuchungInformationClass(buchungsprofiel.getBuchungsID(), neuerFlug.getStartFlughafen().getStadt(), neuerFlug.getZielFlughafen().getStadt(), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAnkunftZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), buchungsprofiel.calculatePreis(), buchungsprofiel.getGepaeckGewicht(), (((BuchungsprofilAnwender) buchungsprofiel).getMitfliegerListe().size() + 1), BenutzerprofilSpeicher.getInstance().getBenutzerprofil(((BuchungsprofilAnwender) buchungsprofiel).getAnwenderPID()).getBenutzerName(), "-"));
            }
        }

        return FXCollections.observableList(buchungInformationClasseList);
    }

    public static ObservableList<BuchungInformationClass> getObjektListAnwender(List<Buchungsprofil> buchungsprofilList) {
        LinkedList<BuchungInformationClass> buchungInformationClasseList = new LinkedList<>();

        for (Buchungsprofil buchungsprofiel : buchungsprofilList) {
            Flug neuerFlug = FluegeSpeicher.getInstance().getFlug(buchungsprofiel.getFlugNummer());
            buchungInformationClasseList.add(new BuchungInformationClass(buchungsprofiel.getBuchungsID(), neuerFlug.getStartFlughafen().getStadt(), neuerFlug.getZielFlughafen().getStadt(), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAnkunftZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), buchungsprofiel.calculatePreis(), buchungsprofiel.getGepaeckGewicht(), (((BuchungsprofilAnwender) buchungsprofiel).getMitfliegerListe().size() + 1)));
        }

        return FXCollections.observableList(buchungInformationClasseList);
    }

    public static ObservableList<BuchungInformationClass> getObjektListAngestellter(List<Buchungsprofil> buchungsprofilList) {
        LinkedList<BuchungInformationClass> buchungInformationClasseList = new LinkedList<>();

        for (Buchungsprofil buchungsprofiel : buchungsprofilList) {
            Flug neuerFlug = FluegeSpeicher.getInstance().getFlug(buchungsprofiel.getFlugNummer());
            buchungInformationClasseList.add(new BuchungInformationClass(buchungsprofiel.getBuchungsID(), neuerFlug.getStartFlughafen().getStadt(), neuerFlug.getZielFlughafen().getStadt(), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAnkunftZeit().format(DateTimeFormatter.ofPattern("HH:mm")), neuerFlug.getAbflugZeit().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), buchungsprofiel.calculatePreis(), buchungsprofiel.getGepaeckGewicht(), (((BuchungsprofilAngestellter) buchungsprofiel).getPassagierListe().size()), (((BuchungsprofilAngestellter) buchungsprofiel).getPassagierListe().get(0).getVorname())));
        }

        return FXCollections.observableList(buchungInformationClasseList);
    }

}
