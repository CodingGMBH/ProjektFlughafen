package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Model.Angestellter;
import It.fallmerayer.codingGmbH.projektFlughafen.Model.Anwender;
import It.fallmerayer.codingGmbH.projektFlughafen.Model.FlugNichtBuchbarException;
import It.fallmerayer.codingGmbH.projektFlughafen.Model.Mitflieger;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.NichtsEingegebenException;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.FlugInformationClass;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by gabriel on 17.04.17.
 */
public class ZahlungController extends AbstractController {
    @FXML Button buchenButt;

    @FXML TextField zahlungsadetenTxtField;

    public static FlugInformationClass hinflugInformationClass;
    public static FlugInformationClass rueckflugInformationClass;
    public static LinkedList<Mitflieger> mitfliegerList;

    public static void setHinflugInformationClass(FlugInformationClass hinflugInformationClass) {
        ZahlungController.hinflugInformationClass = hinflugInformationClass;
    }

    public static void setRueckflugInformationClass(FlugInformationClass rueckflugInformationClass) {
        ZahlungController.rueckflugInformationClass = rueckflugInformationClass;
    }

    public static void setMitfliegerList(LinkedList<Mitflieger> mitfliegerList) {
        ZahlungController.mitfliegerList = mitfliegerList;
    }

    @FXML
    private void handelBuchen() throws IOException {
        if (everythingCorrectInput()) {
            if (main.benutzerprofil instanceof Anwender) {
                if (mitfliegerList == null){
                    mitfliegerList = new LinkedList<>();
                }
                try {
                    ((Anwender) main.benutzerprofil).bucheFlug(hinflugInformationClass.getFlugId(), hinflugInformationClass.getGepaeck(), mitfliegerList);
                    if (rueckflugInformationClass != null){
                        ((Anwender) main.benutzerprofil).bucheFlug(rueckflugInformationClass.getFlugId(), rueckflugInformationClass.getGepaeck(), mitfliegerList);
                    }
                } catch (FlugNichtBuchbarException | IOException e) {
                    main.openMessageDialog(e.getMessage());
                }
                main.selectView(ViewNavigation.ZWISCHENSCENE);
            } else {
                try {
                    ((Angestellter) main.benutzerprofil).bucheFlugFuerKunde(hinflugInformationClass.getFlugId(), hinflugInformationClass.getGepaeck(), mitfliegerList);
                    if (rueckflugInformationClass != null){
                        ((Angestellter) main.benutzerprofil).bucheFlugFuerKunde(rueckflugInformationClass.getFlugId(), rueckflugInformationClass.getGepaeck(), mitfliegerList);
                    }
                } catch (FlugNichtBuchbarException | IOException e) {
                    main.openMessageDialog(e.getMessage());
                }
                main.selectView(ViewNavigation.ZWISCHENSCENE);
            }
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
