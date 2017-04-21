package Flughafen.Controller;

import Flughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * Created by gabriel on 17.04.17.
 */
public class FlugAnsichtController extends AbstractController {
    @FXML Button zurueckButt;
    @FXML Button weiterButt;

    @FXML TableView hinflugTabelView;
    @FXML TableView rueckflugTabelView;

    @FXML Label flugLabel;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleBack(){
        main.selectView(main.lastController);
    }

    @FXML
    private void handleWeiter(){
        main.selectView(ViewNavigation.BUCHUNGSUEBERSICHTSCENE);
    }
}
