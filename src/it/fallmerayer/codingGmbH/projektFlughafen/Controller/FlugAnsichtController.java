package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by gabriel on 17.04.17.
 */
public class FlugAnsichtController extends AbstractController {
    @FXML Button zurueckButt;
    @FXML Button weiterButt;

    @FXML TableView hinflugTabelView;

    @FXML TableColumn vonHinCollumn;
    @FXML TableColumn nachHinCollumn;
    @FXML TableColumn startHinCollumn;
    @FXML TableColumn ankunftHinCollumn;
    @FXML TableColumn datumHinCollumn;
    @FXML TableColumn preisHinCollumn;

    @FXML TableView rueckflugTabelView;

    @FXML TableColumn vonRueckCollumn;
    @FXML TableColumn nachRueckCollumn;
    @FXML TableColumn startRueckCollumn;
    @FXML TableColumn ankunftRueckCollumn;
    @FXML TableColumn datumRueckCollumn;
    @FXML TableColumn preisRueckCollumn;

    @FXML Label flugLabel;

    @FXML
    private void initialize() {
        //TODO add Tabelle
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
