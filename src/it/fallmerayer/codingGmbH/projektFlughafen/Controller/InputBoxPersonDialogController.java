package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
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
    int time = 1;

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
    }

    public void setHowOften(int howOften) {
        this.howOften = howOften;
        personaldatenLabel.setText("Personaldate" + " " + time + "/" + howOften);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @FXML
    private void handleFortfahren(){
        time += 1;
        if (howOften >= time){
            personaldatenLabel.setText("Personaldate" + " " + time + "/" + howOften);
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
