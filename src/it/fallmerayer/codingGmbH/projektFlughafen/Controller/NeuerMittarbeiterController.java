package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (everythingCorrectInput()){
            main.selectView(ViewNavigation.ANGEMELDETSCENE);
        }else{
            System.out.println("Something is wrong");
        }
    }

    private boolean everythingCorrectInput(){
        try {
            CheckValidations.isDate(geburtsdatumDatePicker.getValue());
            CheckValidations.isNameString(vornameTxtField.getText());
            CheckValidations.isNameString(nachnameTxtField.getText());
            CheckValidations.isValidString(benutzernameTxtField.getText());
            CheckValidations.isValidPassword(passwordTxtField.getText());
            CheckValidations.isEmail(emailTxtField.getText());
        }catch (NichtsEingegebenException e){
            return false;
        }
        catch (KeinNameException e){
            return false;
        }
        catch (KeineEmailException e){
            return false;
        }
        catch (KeinPasswordException e){
            return false;
        }
        catch (KeinDatumException e){
            return false;
        }
        return true;
    }
}
