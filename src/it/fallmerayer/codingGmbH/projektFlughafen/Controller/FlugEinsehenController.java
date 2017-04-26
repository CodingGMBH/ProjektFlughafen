package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.BuchungsprofileSpeicher;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.FluegeSpeicher;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.FlugNichtBuchbarException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.BuchungInformationClass;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by gabriel on 17.04.17.
 */
public class FlugEinsehenController extends AbstractController {
    @FXML Label flugVonNachLabel;
    @FXML Label preisFureHinflugLabel;
    @FXML Label anzahlDerPersonHinflugLabel;
    @FXML Label datumHinflugLabel;
    @FXML Label gepaeckstueckeHinflugLabel;
    @FXML Label flugzeugTypHinflugLabel;

    @FXML Button backButt;
    @FXML Button gepaeckBearbeitenButt;

    private static BuchungInformationClass buchungshistorie;

    private static Double gepaeckGewicht;

    public static void setBuchungshistorie(BuchungInformationClass buchungshistorie) {
        FlugEinsehenController.buchungshistorie = buchungshistorie;
    }

    public static void setGepaeckGewicht(Double gepaeckGewicht) {
        FlugEinsehenController.gepaeckGewicht = gepaeckGewicht;
    }

    @Override
    public void startController() {
        flugVonNachLabel.setText(HelpfullStrings.FLUGVONSTRING + buchungshistorie.getStartOrt() + HelpfullStrings.FLUGNACHSTRING + buchungshistorie.getZielOrt() + HelpfullStrings.UMSTRING + buchungshistorie.getStartZeit() + " - " + buchungshistorie.getAnkunftsZeit() + HelpfullStrings.UHRSTRING);
        preisFureHinflugLabel.setText(HelpfullStrings.PREISFUERFLUGSTRING + BuchungsprofileSpeicher.getInstance().getBuchungsprofil(buchungshistorie.getBuchungsID()).calculatePreis());
        anzahlDerPersonHinflugLabel.setText(HelpfullStrings.ANZAHLDERPERSONENSTRING + buchungshistorie.getPersonenAnzahl());
        datumHinflugLabel.setText(HelpfullStrings.DATUMSTRING + buchungshistorie.getDatum());
        gepaeckstueckeHinflugLabel.setText(HelpfullStrings.GEPAECKSTUECKSTRING + buchungshistorie.getGepaeck());
        flugzeugTypHinflugLabel.setText(FluegeSpeicher.getInstance().getFlug(BuchungsprofileSpeicher.getInstance().getBuchungsprofil(buchungshistorie.getBuchungsID()).getFlugNummer()).getFlugzeug().getFlugGesellschaft());
    }

    @FXML
    private void handelBack(){
        main.selectView(main.lastController);
    }


    @FXML
    private void handelGepackBearbeiten(){
        main.openGepaeckDialog("Flug");
        try {
            BuchungsprofileSpeicher.getInstance().getBuchungsprofil(buchungshistorie.getBuchungsID()).setGepaeckGewicht(gepaeckGewicht);
            gepaeckstueckeHinflugLabel.setText(HelpfullStrings.GEPAECKSTUECKSTRING + gepaeckGewicht);
        } catch (FlugNichtBuchbarException e) {
            main.openMessageDialog(e.getMessage());
        }
    }
}
