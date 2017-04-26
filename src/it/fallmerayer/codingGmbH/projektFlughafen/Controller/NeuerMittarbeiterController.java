package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Administrator;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.TooMuchAngestellteException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by gabriel on 17.04.17.
 */
//Finished
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

    @Override
    public void startController() {
        angemeldetAlsLabel.setText(HelpfullStrings.ANGEMELDETALSSTRING + main.benutzerprofil.getBenutzerName());
    }

    @FXML
    private void handleBack(){
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }

    @FXML
    private void handleHinzufuegen(){
        if (everythingCorrectInput()){
            if (adminRadioButton.isSelected()){
                try {
                    ((Administrator)main.benutzerprofil).erstelleAdministratorKonto(vornameTxtField.getText(), nachnameTxtField.getText(), emailTxtField.getText(), geburtsdatumDatePicker.getValue().atStartOfDay(), benutzernameTxtField.getText(), passwordTxtField.getText());
                } catch (TooMuchAngestellteException e) {
                    main.openMessageDialog(e.getMessage());
                }
            }else{
                try {
                    ((Administrator)main.benutzerprofil).erstelleAngestelltenKonto(vornameTxtField.getText(), nachnameTxtField.getText(), emailTxtField.getText(), geburtsdatumDatePicker.getValue().atStartOfDay(), benutzernameTxtField.getText(), passwordTxtField.getText());
                } catch (TooMuchAngestellteException e) {
                    main.openMessageDialog(e.getMessage());
                }
            }
            main.selectView(ViewNavigation.ANGEMELDETSCENE);
        }else{
            System.out.println("Something is wrong");
        }
    }

    private boolean everythingCorrectInput(){
        try {
            CheckValidations.isValidString(vornameTxtField.getText());
            CheckValidations.isValidString(nachnameTxtField.getText());
            CheckValidations.isValidString(benutzernameTxtField.getText());
            CheckValidations.isValidString(passwordTxtField.getText());
            CheckValidations.isValidString(emailTxtField.getText());

            CheckValidations.isNameString(vornameTxtField.getText());
            CheckValidations.isNameString(nachnameTxtField.getText());
            CheckValidations.isEmail(emailTxtField.getText());
            CheckValidations.isValidPassword(passwordTxtField.getText());
            CheckValidations.isDate(geburtsdatumDatePicker.getValue());

        }catch (NichtsEingegebenException | KeinPasswordException | KeinNameException | KeineEmailException | KeinDatumException e){
            main.openMessageDialog(e.getMessage());
            return false;
        }
        return true;
    }
}
