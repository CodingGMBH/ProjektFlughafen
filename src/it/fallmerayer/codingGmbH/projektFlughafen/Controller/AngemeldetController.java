package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

/**
 * Created by gabriel on 17.04.17.
 */
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

    @FXML
    private void handleImageViewClicked(){
        System.out.println("pressed");
    }

    @FXML
    private void handleClickBuchungshistorie(){
        main.selectView(ViewNavigation.BUCHUNGSHISTORIEADMINSCENE);
    }

    @FXML
    private void handleClickSuchen(){
        if (hinUndRueckflugRadioButt.isSelected()){
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
}
