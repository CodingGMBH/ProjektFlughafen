package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Modell.Mitflieger;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gabriel on 17.04.17.
 */
public class InputBoxPersonDialogController extends AbstractController {
    Stage dialogStage;
    String person;

    LinkedList<Mitflieger> mitfliegerList = new LinkedList<>();

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
        if (everythingCorrectInputReg()) {
            time += 1;
            mitfliegerList.add(new Mitflieger(vornameTxtField.getText(), nachnameTxtField.getText(), emailTxtField.getText(), geburtsdatumDatePicker.getValue().atStartOfDay(), Integer.parseInt(nummerDerIdentitaetskartenTxtField.getText())));
            if (howOften >= time) {
                personaldatenLabel.setText("Personaldate" + " " + time + "/" + howOften);
                vornameTxtField.clear();
                nachnameTxtField.clear();
                emailTxtField.clear();
                nummerDerIdentitaetskartenTxtField.clear();
                geburtsdatumDatePicker.setValue(null);
            } else {
                ZahlungController.setMitfliegerList(mitfliegerList);
                dialogStage.close();
                if (main.benutzerprofil != null) {
                    System.out.println(main.benutzerprofil);
                    main.selectView(ViewNavigation.ZAHLUNGSCENE);
                } else {
                    main.selectView(ViewNavigation.ANMELDENREGISTRIRENSCENE);
                }
            }
        }
    }

    @FXML
    private void handleAbbrechen(){
        dialogStage.close();
    }

    private boolean everythingCorrectInputReg(){
        try {
            CheckValidations.isValidString(vornameTxtField.getText());
            CheckValidations.isValidString(nachnameTxtField.getText());
            CheckValidations.isValidString(nummerDerIdentitaetskartenTxtField.getText());
            CheckValidations.isValidString(emailTxtField.getText());

            CheckValidations.isNameString(vornameTxtField.getText());
            CheckValidations.isNameString(nachnameTxtField.getText());
            CheckValidations.isEmail(emailTxtField.getText());

            CheckValidations.isDate(geburtsdatumDatePicker.getValue());

        }catch (NichtsEingegebenException | KeinNameException | KeineEmailException | KeinDatumException e){
            main.openMessageDialog(e.getMessage());
            return false;
        }
        return true;
    }
}
