package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


/**
 * Created by gabriel on 17.04.17.
 */
public class ZwischenSceneController extends AbstractController {
    @FXML Label erfolgreichLabel;

    @FXML
    private void initialize() {

    }

    public void goOn(){
        if (main.lastController.matches(ViewNavigation.ZAHLUNGSCENE)){
            erfolgreichLabel.setText("Zahlung erfolgreich");
        } else if (main.lastController.matches(ViewNavigation.ANMELDENREGISTRIRENSCENE)){
            erfolgreichLabel.setText("Erfolgreich angemeldet/registriert");
        }
    }

    @FXML
    private void handleGoOn() throws IOException {
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }
}
