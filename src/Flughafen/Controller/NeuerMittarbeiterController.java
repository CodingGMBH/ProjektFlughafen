package Flughafen.Controller;

import Flughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by gabriel on 17.04.17.
 */
public class NeuerMittarbeiterController extends AbstractController {
    @FXML TextField vornameTxtField;
    @FXML TextField nachnameTxtField;
    @FXML TextField benutzernameTxtField;
    @FXML TextField passwordTxtField;
    @FXML TextField emailTxtField;

    @FXML Label angemeldetAlsLabel;

    @FXML DatePicker geburtsdatumDatePicker;

    @FXML RadioButton adminRadioButton;
    @FXML RadioButton angestellterRadioButton;

    @FXML Button backButt;
    @FXML Button hinzufuegenButt;

    @FXML
    private void handleBack(){
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }

    @FXML
    private void handleHinzufuegen(){
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }
}
