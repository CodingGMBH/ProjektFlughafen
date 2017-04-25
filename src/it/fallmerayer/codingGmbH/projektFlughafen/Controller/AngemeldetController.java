package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.*;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeinDatumException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeineNummerException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeineStadtException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.NichtsEingegebenException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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

    @Override
    public void startController() {
        if (main.benutzerprofil instanceof Administrator){
            Image image = new Image("files/Mitarbeiterhinzufugen.png");
            imageView.setImage(image);
        }else if (main.benutzerprofil instanceof Angestellter){
            Image image = new Image("");
            imageView.setImage(image);
        }else if (main.benutzerprofil instanceof Anwender){
            Image image = new Image("files/Warenkorb.png");
            imageView.setImage(image);
        }
        angemeldetAlsLabel.setText(HelpfullStrings.angemeldetAlsString + main.benutzerprofil.getBenutzerName());
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
        main.selectView(ViewNavigation.BUCHUNGSHISTORIEADMINSCENE);
    }

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
