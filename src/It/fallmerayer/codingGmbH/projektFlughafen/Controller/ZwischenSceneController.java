package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


/**
 * Created by gabriel on 17.04.17.
 */

//Finished
public class ZwischenSceneController extends AbstractController {
    @FXML Label erfolgreichLabel;

    @Override
    public void startController(){
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
