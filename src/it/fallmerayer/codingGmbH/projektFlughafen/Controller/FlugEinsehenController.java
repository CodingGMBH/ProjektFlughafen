package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

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

    //Todo Add FlugEinsehen
    @FXML
    private void handelBack(){
        main.selectView(main.lastController);
    }

    @FXML
    private void handelGepackBearbeiten(){
        main.openGepaeckDialog("Flug");
    }
}
