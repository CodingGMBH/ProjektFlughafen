package Flughafen.Controller;

import Flughafen.Utility.MainApp;
import Flughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartSceneController extends AbstractController {
    @FXML private Button AnmeldenRegistrierenButton;



    @FXML
    private void handleAnmeldenRegistrieren() throws IOException {
        main.selectView(ViewNavigation.ANMELDENREGISTRIRENSCENE);
    }

}
