package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Created by gabriel on 16.04.17.
 */
public class AnmeldenRegistrierenController extends AbstractController {
    @FXML TextField vornameTxtField;
    @FXML TextField passwordTxtField;
    @FXML TextField passwordRegTxtField;
    @FXML TextField nummerDerIdentitaetskarteTxtField;
    @FXML TextField nachnameTxtField;
    @FXML TextField emailTxtField;
    @FXML TextField benutzernameTxtField;
    @FXML TextField benutzernameRegTxtField;

    @FXML DatePicker geburtsdatumDatePicker;

    @FXML Button registrierenButt;
    @FXML Button anmeldenButt;

    @FXML
    private void handleAnmelden(){
        main.selectView(ViewNavigation.ZWISCHENSCENE);
    }

    @FXML
    private void handleRegistrieren(){
        main.selectView(ViewNavigation.ZWISCHENSCENE);
    }

}
