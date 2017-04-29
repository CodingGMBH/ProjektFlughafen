package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by gabriel on 17.04.17.
 */
public class InputBoxGepaeckDialogController extends AbstractController {
    Stage dialogStage;
    String flug;

    @FXML TextField gewichtAendern;

    @FXML Button abbrechenButt;
    @FXML Button fortfahrenButt;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setFlug(String flug) {
        this.flug = flug;
    }

    @FXML
    private void handleFortfahren(){
        if (everythingCorrectInputReg()){
            if (main.currentController.equals(ViewNavigation.BUCHUNGSUEBERSICHTSCENE)){
                BuchungsuebersichtController.setGepaeckGewicht(Double.parseDouble(gewichtAendern.getText()));
            } else if (main.currentController.equals(ViewNavigation.FLUGEINSEHENSCENE)){
                FlugEinsehenController.setGepaeckGewicht(Double.parseDouble(gewichtAendern.getText()));
            }
            dialogStage.close();
        }
    }

    @FXML
    private void handleAbbrechen(){
        dialogStage.close();
    }

    private boolean everythingCorrectInputReg(){
        try {
            CheckValidations.isValidString(gewichtAendern.getText());

            CheckValidations.isKommaNumber(gewichtAendern.getText());

        }catch (NichtsEingegebenException | KeineNummerException e){
            main.openMessageDialog(e.getMessage());
            return false;
        }
        return true;
    }
}
