package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

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
        if (hinUndRueckflugRadioButt.isPressed()){
            main.selectView(ViewNavigation.FLUGANSICHTSCENE);
        }else {
            main.selectView(ViewNavigation.FLUGANSICHTNURHINFLUGSCENE);
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

}
