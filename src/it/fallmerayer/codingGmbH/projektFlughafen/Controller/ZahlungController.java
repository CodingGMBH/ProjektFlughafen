package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeinDatumException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeineNummerException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.KeineStadtException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.NichtsEingegebenException;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by gabriel on 17.04.17.
 */
public class ZahlungController extends AbstractController {
    @FXML Button buchenButt;

    @FXML TextField zahlungsadetenTxtField;
    //TODO Zahlung finishen

    @FXML
    private void handelBuchen() throws IOException {
        if (everythingCorrectInput()) {
            main.selectView(ViewNavigation.ZWISCHENSCENE);
        }
    }

    private boolean everythingCorrectInput(){
        try {
            CheckValidations.isValidString(zahlungsadetenTxtField.getText());

        }catch (NichtsEingegebenException e){
            main.openMessageDialog(e.getMessage());
            return false;
        }
        return true;
    }
}
