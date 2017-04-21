package Flughafen.Controller;

import Flughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by gabriel on 17.04.17.
 */
public class InputBoxPersonDialogController extends AbstractController {
    Stage dialogStage;
    String person;

    int howOften;
    int time;

    @FXML Label personaldatenLabel;

    @FXML TextField vornameTxtField;
    @FXML TextField nachnameTxtField;
    @FXML TextField emailTxtField;
    @FXML TextField nummerDerIdentitaetskartenTxtField;

    @FXML DatePicker geburtsdatumDatePicker;

    @FXML Button abbrechenButt;
    @FXML Button fortfahrenButt;

    @FXML
    private void initialize() {
        time = 1;
        personaldatenLabel.setText(personaldatenLabel.getText() + " " + time + "/" + howOften);
    }

    public void setHowOften(int howOften) {
        this.howOften = howOften;
        time = 0;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @FXML
    private void handleFortfahren(){
        time++;
        if (howOften > time){

        }else {
            dialogStage.close();
            main.selectView(ViewNavigation.ZAHLUNGSCENE);
        }
    }

    @FXML
    private void handleAbbrechen(){
        dialogStage.close();
    }
}
