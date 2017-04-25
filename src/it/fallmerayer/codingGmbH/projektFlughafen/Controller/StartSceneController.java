package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.FluegeSpeicher;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Flughafen;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

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
            main.flugList = FluegeSpeicher.getInstance().getGefilterteFluege(new Flughafen(standortTxtField.getText()), new Flughafen(zielortTxtField.getText()), datumDesStartsDatePicker.getValue().atStartOfDay(), Integer.parseInt(anzahlDerPersonenTxtField.getText()), Double.parseDouble(gepaeckgewichtTxtField.getText()));
            if (hinUndRueckflugRadioButt.isSelected()) {
                main.rueckflugList = FluegeSpeicher.getInstance().getGefilterteFluege(new Flughafen(zielortTxtField.getText()), new Flughafen(standortTxtField.getText()), datumDerRueckkehrDatePicker.getValue().atStartOfDay(), Integer.parseInt(anzahlDerPersonenTxtField.getText()), Double.parseDouble(gepaeckgewichtTxtField.getText()));
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
