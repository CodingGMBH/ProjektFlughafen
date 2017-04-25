package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Flug;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Flughafen;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;


/**
 * Created by gabriel on 17.04.17.
 */
public class FlugAnsichtNurHinflugController extends AbstractController {
    @FXML Button zurueckButt;
    @FXML Button weiterButt;

    @FXML TableView<Flug> hinflugTabelView;

    @FXML TableColumn<Flug, Flughafen> vonCollumn;
    @FXML TableColumn<Flug, Flughafen> nachCollumn;
    @FXML TableColumn startCollumn;
    @FXML TableColumn ankunftCollumn;
    @FXML TableColumn datumCollumn;
    @FXML TableColumn preisCollumn;

    @FXML Label flugLabel;

    //TODO add Tabelle

    @FXML private void initialize() {
        ObservableList<Flug> oListStavaka = FXCollections.observableArrayList(main.flugList);



//        private Flughafen startFlughafen;
//        private Flughafen zielFlughafen;
//        private double preisSitzplatz;
//        private LocalDateTime abflugZeit;
//        private LocalDateTime ankunftZeit;
//        private int zaehlerGebuchteSitzplaetze;
//        private double zaehlerGepaeckGewicht;
//        private boolean buchbar;
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
