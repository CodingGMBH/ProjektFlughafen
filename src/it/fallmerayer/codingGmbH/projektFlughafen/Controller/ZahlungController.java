package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by gabriel on 17.04.17.
 */
public class ZahlungController extends AbstractController {
    @FXML Button buchenButt;

    @FXML TextField zahlungsadetenTxtField;

    @FXML
    private void handelBuchen() throws IOException {
        main.selectView(ViewNavigation.ZWISCHENSCENE);
    }
}
