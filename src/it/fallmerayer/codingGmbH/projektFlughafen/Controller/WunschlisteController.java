package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * Created by gabriel on 17.04.17.
 */
public class WunschlisteController extends AbstractController {
    @FXML Button backButt;
    @FXML Button goButt;
    @FXML Button loeschenButt;

    @FXML TableView buchungTabelView;

    @FXML Label angemeldetLabel;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleBack(){
        main.selectView(main.lastController);
    }

    @FXML
    private void handleGo(){
        main.selectView(ViewNavigation.BUCHUNGSUEBERSICHTSCENE);
    }

    @FXML
    private void handleLoeschen(){

    }
}
