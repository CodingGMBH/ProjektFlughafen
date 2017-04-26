package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.BuchungInformationClass;
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

    static BuchungInformationClass buchungshistorie;

    //Todo Add FlugEinsehen

    @Override
    public void startController() {
//        if (BuchungsprofileSpeicher.getInstance().getBuchungsprofil(buchungshistorie.getBuchungsID()) ){
//
//        }
    }

    @FXML
    private void handelBack(){
        main.selectView(main.lastController);
    }

    @FXML
    private void handelGepackBearbeiten(){
        main.openGepaeckDialog("Flug");
    }
}
