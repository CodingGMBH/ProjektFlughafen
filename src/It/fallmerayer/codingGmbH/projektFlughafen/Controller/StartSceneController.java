package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.FlugInformationClass;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

//Finished
public class StartSceneController extends AbstractController {
    @FXML TextField standortTxtField;
    @FXML TextField zielortTxtField;
    @FXML TextField gepaeckgewichtTxtField;
    @FXML TextField anzahlDerPersonenTxtField;

    @FXML RadioButton hinUndRueckflugRadioButt;
    @FXML RadioButton hinflugRadioButt;

    @FXML Label datumderRueckkehrLabel;

    @FXML DatePicker datumDesStartsDatePicker;
    @FXML DatePicker datumDerRueckkehrDatePicker;

    @FXML Button suchenButt;
    @FXML Button AnmeldenRegistrierenButton;


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


    @FXML
    private void handleAnmeldenRegistrieren() throws IOException {
        main.selectView(ViewNavigation.ANMELDENREGISTRIRENSCENE);
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
