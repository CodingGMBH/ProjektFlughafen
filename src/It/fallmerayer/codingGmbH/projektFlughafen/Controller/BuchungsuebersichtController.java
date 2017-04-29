package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Model.*;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.FlugInformationClass;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by gabriel on 17.04.17.
 */
//Fertig
public class BuchungsuebersichtController extends AbstractController {

    @FXML Label flugVonNachLabel;
    @FXML Label preisFureHinflugLabel;
    @FXML Label anzahlDerPersonHinflugLabel;
    @FXML Label datumHinflugLabel;
    @FXML Label gepaeckstueckeHinflugLabel;
    @FXML Label flugzeugTypHinflugLabel;
    @FXML Label rueckflugVonNachLabel;
    @FXML Label preisFuerRueckflugLabel;
    @FXML Label anzahlDerPersonenRueckflugLabel;
    @FXML Label datumRucekflugLabel;
    @FXML Label flugzeugtypRueckflugLabel;
    @FXML Label gepackstuckRueckflugLabel;

    @FXML Button backButt;
    @FXML Button inWunschButt;
    @FXML Button gepaeckBearbeitenButt;
    @FXML Button fertigstellenButt;

    private static FlugInformationClass hinflug;
    private static FlugInformationClass rueckflug;
    private static Double gepaeckGewicht;

    public static void setHinflug(FlugInformationClass hinflug) {
        BuchungsuebersichtController.hinflug = hinflug;
    }

    public static void setRueckflug(FlugInformationClass rueckflug) {
        BuchungsuebersichtController.rueckflug = rueckflug;
    }

    public static void setGepaeckGewicht(double gepaeckGewicht) {
        BuchungsuebersichtController.gepaeckGewicht = gepaeckGewicht;
    }

    @Override
    public void startController() {
        flugVonNachLabel.setText(HelpfullStrings.FLUGVONSTRING + hinflug.getStartOrt() + HelpfullStrings.FLUGNACHSTRING + hinflug.getZielOrt() + HelpfullStrings.UMSTRING + hinflug.getStartZeit() + " - " + hinflug.getAnkunftsZeit() + HelpfullStrings.UHRSTRING);
        preisFureHinflugLabel.setText(HelpfullStrings.PREISFUERFLUGSTRING + hinflug.calulatePreis());
        anzahlDerPersonHinflugLabel.setText(HelpfullStrings.ANZAHLDERPERSONENSTRING + hinflug.getPersonenAnzahl());
        datumHinflugLabel.setText(HelpfullStrings.DATUMSTRING + hinflug.getDatum());
        gepaeckstueckeHinflugLabel.setText(HelpfullStrings.GEPAECKSTUECKSTRING + hinflug.getGepaeck());
        flugzeugTypHinflugLabel.setText(hinflug.getFlugGesellschaft());

        if (rueckflug != null){
            rueckflugVonNachLabel.setText(HelpfullStrings.FLUGVONSTRING + rueckflug.getStartOrt() + HelpfullStrings.FLUGNACHSTRING + rueckflug.getZielOrt() + HelpfullStrings.UMSTRING + rueckflug.getStartZeit() + " - " + rueckflug.getAnkunftsZeit() + HelpfullStrings.UHRSTRING);
            preisFuerRueckflugLabel.setText(HelpfullStrings.PREISFUERFLUGSTRING + rueckflug.calulatePreis());
            anzahlDerPersonenRueckflugLabel.setText(HelpfullStrings.ANZAHLDERPERSONENSTRING + rueckflug.getPersonenAnzahl());
            datumRucekflugLabel.setText(HelpfullStrings.DATUMSTRING + rueckflug.getDatum());
            gepackstuckRueckflugLabel.setText(HelpfullStrings.GEPAECKSTUECKSTRING + rueckflug.getGepaeck());
            flugzeugtypRueckflugLabel.setText(rueckflug.getFlugGesellschaft());
        }else{
            rueckflugVonNachLabel.setVisible(false);
            preisFuerRueckflugLabel.setVisible(false);
            anzahlDerPersonenRueckflugLabel.setVisible(false);
            datumRucekflugLabel.setVisible(false);
            gepackstuckRueckflugLabel.setVisible(false);
            flugzeugtypRueckflugLabel.setVisible(false);
        }

        if (main.benutzerprofil instanceof Administrator || main.benutzerprofil instanceof Angestellter || main.benutzerprofil == null || main.lastController.equals(ViewNavigation.WUNSCHLISTESCENE)){
            inWunschButt.setVisible(false);
            inWunschButt.setDisable(true);
        }
    }

    @FXML
    private void handelBack(){
        if (main.lastController.equals(ViewNavigation.WUNSCHLISTESCENE)){
            ((Anwender) main.benutzerprofil).legeFlugInWunschliste(hinflug.getFlugId(), hinflug.getPersonenAnzahl(), hinflug.getGepaeck());
        }
        main.selectView(main.lastController);
    }
    @FXML
    private void handelInWunschliste(){
        ((Anwender) main.benutzerprofil).legeFlugInWunschliste(hinflug.getFlugId(), hinflug.getPersonenAnzahl(), hinflug.getGepaeck());
        if (rueckflug != null){
            ((Anwender) main.benutzerprofil).legeFlugInWunschliste(rueckflug.getFlugId(), rueckflug.getPersonenAnzahl(), rueckflug.getGepaeck());
        }
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }
    @FXML
    private void handelGepackBearbeiten(){
        main.openGepaeckDialog("Flug");
        if (gepaeckGewicht != null) {
            if (rueckflug != null) {
                if (FluegeSpeicher.getInstance().getFlug(hinflug.getFlugId()).getFreiesGepaeckGewicht() + hinflug.getGepaeck() > gepaeckGewicht && FluegeSpeicher.getInstance().getFlug(rueckflug.getFlugId()).getFreiesGepaeckGewicht() + rueckflug.getGepaeck() > gepaeckGewicht) {
                    hinflug.setGepaeck(gepaeckGewicht);
                    gepaeckstueckeHinflugLabel.setText(HelpfullStrings.GEPAECKSTUECKSTRING + gepaeckGewicht);
                    preisFureHinflugLabel.setText(HelpfullStrings.PREISFUERFLUGSTRING + hinflug.calulatePreis());

                    rueckflug.setGepaeck(gepaeckGewicht);
                    gepackstuckRueckflugLabel.setText(HelpfullStrings.GEPAECKSTUECKSTRING + gepaeckGewicht);
                    preisFuerRueckflugLabel.setText(HelpfullStrings.PREISFUERFLUGSTRING + rueckflug.calulatePreis());
                }
            } else {
                if (FluegeSpeicher.getInstance().getFlug(hinflug.getFlugId()).getFreiesGepaeckGewicht() + hinflug.getGepaeck() > gepaeckGewicht) {
                    hinflug.setGepaeck(gepaeckGewicht);
                    gepaeckstueckeHinflugLabel.setText(HelpfullStrings.GEPAECKSTUECKSTRING + gepaeckGewicht);
                    preisFureHinflugLabel.setText(HelpfullStrings.PREISFUERFLUGSTRING + hinflug.calulatePreis());
                }
            }
        }
    }
    @FXML
    private void handelFertigstellen(){
        ZahlungController.setHinflugInformationClass(hinflug);

        if(main.benutzerprofil instanceof Anwender || main.benutzerprofil == null){
            main.openPersonDialpg("Person", hinflug.getPersonenAnzahl() - 1);
        }else {
            main.openPersonDialpg("Person", hinflug.getPersonenAnzahl());
        }
    }

}
