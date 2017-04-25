package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by gabriel on 17.04.17.
 */
public class BuchungshistorieAngestellterController extends AbstractController {
    @FXML Button backButt;
    @FXML Button goButt;

    @FXML TableView buchungTabelView;

    @FXML Label angemeldetLabel;

    @FXML TableColumn vonCollumn;
    @FXML TableColumn nachCollumn;
    @FXML TableColumn startCollumn;
    @FXML TableColumn ankunftCollumn;
    @FXML TableColumn datumCollumn;
    @FXML TableColumn preisCollumn;
    @FXML TableColumn gepaeckCollumn;
    @FXML TableColumn personenCollumn;
    @FXML TableColumn gebuchtFuerCollumn;


    //TODO add Tabelle
    @Override
    public void startController() {
        angemeldetLabel.setText(HelpfullStrings.angemeldetAlsString + main.benutzerprofil.getBenutzerName());
    }

    @FXML
    private void handleBack(){
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }

    @FXML
    private void handleGo(){
        main.selectView(ViewNavigation.FLUGEINSEHENSCENE);
    }
}
