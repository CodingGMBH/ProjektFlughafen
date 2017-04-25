package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by gabriel on 17.04.17.
 */
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


    //TODO add ausgewählter FLug

    @FXML
    private void handelBack(){
        main.selectView(main.lastController);
    }
    @FXML
    private void handelInWunschliste(){
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }
    @FXML
    private void handelGepackBearbeiten(){
        main.openGepaeckDialog("Flug");
    }
    @FXML
    private void handelFertigstellen(){
        main.openPersonDialpg("Person", 4);
    }

}
