package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Angestellter;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Anwender;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.WunschlistenEintrag;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * Created by gabriel on 17.04.17.
 */
public class WunschlisteController extends AbstractController {
    @FXML Button backButt;
    @FXML Button goButt;
    @FXML Button loeschenButt;

    @FXML TableView buchungTabelView;

    @FXML TableColumn vonCollumn;
    @FXML TableColumn nachCollumn;
    @FXML TableColumn startCollumn;
    @FXML TableColumn ankunftCollumn;
    @FXML TableColumn datumCollumn;
    @FXML TableColumn preisCollumn;
    @FXML TableColumn gepaeckCollumn;
    @FXML TableColumn personenCollumn;

    @FXML Label angemeldetLabel;

    ObservableList<WunschlistenEintrag> wunschlistenEintragList;

    //TODO add Tabelle


    @Override
    public void startController() {
        angemeldetLabel.setText(HelpfullStrings.angemeldetAlsString + main.benutzerprofil.getBenutzerName());
        wunschlistenEintragList = FXCollections.observableArrayList(((Anwender) main.benutzerprofil).getWunschliste());
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


//    deleteButton.setOnAction(e -> {
//        MyDataType selectedItem = table.getSelectionModel().getSelectedItem();
//        table.getItems().remove(selectedItem);
//    });
}
