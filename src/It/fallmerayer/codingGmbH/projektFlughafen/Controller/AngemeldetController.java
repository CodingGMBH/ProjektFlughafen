package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Modell.*;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeinDatumException;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeineNummerException;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeineStadtException;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.NichtsEingegebenException;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.FlugInformationClass;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.time.format.DateTimeFormatter;

/**
 * Created by gabriel on 17.04.17.
 */

//Finished
public class AngemeldetController extends AbstractController {

    @FXML TextField standortTxtField;
    @FXML TextField zielortTxtField;
    @FXML TextField gepaeckgewichtTxtField;
    @FXML TextField anzahlDerPersonenTxtField;

    @FXML RadioButton hinUndRueckflugRadioButt;
    @FXML RadioButton hinflugRadioButt;

    @FXML Label angemeldetAlsLabel;
    @FXML Label datumderRueckkehrLabel;

    @FXML ImageView imageView;

    @FXML DatePicker datumDesStartsDatePicker;
    @FXML DatePicker datumDerRueckkehrDatePicker;

    @FXML Button suchenButt;
    @FXML Button buchungshistorieButt;

    @FXML
    private void initialize() {
//        imageView.setImage(new Image(""));

    }

    @Override
    public void startController() {
        if (main.benutzerprofil instanceof Administrator){
            Image image = new Image(".." + File.separator + "files" + File.separator + "Mitarbeiterhinzufugen.png");
            imageView.setImage(image);
        }else if (main.benutzerprofil instanceof Angestellter){

        }else if (main.benutzerprofil instanceof Anwender){
            Image image = new Image(".." + File.separator + "files" + File.separator + "Warenkorb.png");
            imageView.setImage(image);
        }
        angemeldetAlsLabel.setText(HelpfullStrings.ANGEMELDETALSSTRING + main.benutzerprofil.getBenutzerName());
    }

    @FXML
    private void handleImageViewClicked(){
        if (main.benutzerprofil instanceof Administrator){
            main.selectView(ViewNavigation.NEUERMITARBEITERSCENE);
        }else if (main.benutzerprofil instanceof Anwender){
            main.selectView(ViewNavigation.WUNSCHLISTESCENE);
        }
    }

    @FXML
    private void handleClickBuchungshistorie(){
        if (main.benutzerprofil instanceof Administrator){
            main.selectView(ViewNavigation.BUCHUNGSHISTORIEADMINSCENE);
        }else if (main.benutzerprofil instanceof Angestellter){
            main.selectView(ViewNavigation.BUCHUNGSHISTORIEANGESTELLTERSCENE);
        }else if (main.benutzerprofil instanceof Anwender){
            main.selectView(ViewNavigation.BUCHUNGSHISTORIENORMSCENE);
        }
    }

    @FXML
    private void handleClickSuchen(){
        if (everythingCorrectInput()) {
            main.hinflugInformation = new FlugInformationClass(standortTxtField.getText(), zielortTxtField.getText(), "00:00", "00:00", datumDesStartsDatePicker.getValue().atStartOfDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), 0, Double.parseDouble(gepaeckgewichtTxtField.getText()), Integer.parseInt(anzahlDerPersonenTxtField.getText()));
            if (hinUndRueckflugRadioButt.isSelected()) {
                main.rueckflugInformation = new FlugInformationClass(zielortTxtField.getText(), standortTxtField.getText(), "00:00", "00:00", datumDerRueckkehrDatePicker.getValue().atStartOfDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), 0, Double.parseDouble(gepaeckgewichtTxtField.getText()), Integer.parseInt(anzahlDerPersonenTxtField.getText()));
                main.selectView(ViewNavigation.FLUGANSICHTSCENE);
            } else {
                main.selectView(ViewNavigation.FLUGANSICHTNURHINFLUGSCENE);
            }
        }
    }

    @FXML
    private void handleHinUndRueckflug(){
        datumDerRueckkehrDatePicker.setVisible(true);
        datumderRueckkehrLabel.setVisible(true);
    }

    @FXML
    private void handleNurHinflug(){
        datumDerRueckkehrDatePicker.setVisible(false);
        datumderRueckkehrLabel.setVisible(false);
    }

    private boolean everythingCorrectInput(){
        try {
            CheckValidations.isValidString(standortTxtField.getText());
            CheckValidations.isValidString(zielortTxtField.getText());
            CheckValidations.isValidString(gepaeckgewichtTxtField.getText());
            CheckValidations.isValidString(anzahlDerPersonenTxtField.getText());

            CheckValidations.isCity(standortTxtField.getText());
            CheckValidations.isCity(zielortTxtField.getText());

            CheckValidations.isKommaNumber(gepaeckgewichtTxtField.getText());
            CheckValidations.isKommaNumber(anzahlDerPersonenTxtField.getText());

            CheckValidations.isDate(datumDesStartsDatePicker.getValue());

            if (hinUndRueckflugRadioButt.isSelected()){
                CheckValidations.isDate(datumDerRueckkehrDatePicker.getValue());
            }
        }catch (NichtsEingegebenException | KeineStadtException | KeineNummerException | KeinDatumException e){
            main.openMessageDialog(e.getMessage());
            return false;
        }
        return true;
    }
}
