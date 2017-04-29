package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Model.Angestellter;
import It.fallmerayer.codingGmbH.projektFlughafen.Model.Anwender;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.FlugInformationClass;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by gabriel on 17.04.17.
 */
public class WunschlisteController extends AbstractController {
    @FXML Button backButt;
    @FXML Button goButt;
    @FXML Button loeschenButt;

    @FXML TableView<FlugInformationClass>buchungTabelView;

    @FXML TableColumn<FlugInformationClass, String> vonCollumn;
    @FXML TableColumn<FlugInformationClass, String> nachCollumn;
    @FXML TableColumn<FlugInformationClass, String> startCollumn;
    @FXML TableColumn<FlugInformationClass, String> ankunftCollumn;
    @FXML TableColumn<FlugInformationClass, String> datumCollumn;
    @FXML TableColumn<FlugInformationClass, Double> preisCollumn;
    @FXML TableColumn<FlugInformationClass, Double> gepaeckCollumn;
    @FXML TableColumn<FlugInformationClass, Integer> personenCollumn;


    @FXML Label angemeldetLabel;

    ObservableList<FlugInformationClass> wunschlistenEintragList;

    @Override
    public void startController() {
        angemeldetLabel.setText(HelpfullStrings.ANGEMELDETALSSTRING + main.benutzerprofil.getBenutzerName());
        ((Anwender) main.benutzerprofil).aktualisiereWunschliste();

        wunschlistenEintragList = FlugInformationClass.getWunschlistenObjektList(((Anwender) main.benutzerprofil).getWunschliste());
        vonCollumn.setCellValueFactory(new PropertyValueFactory<>("startOrt"));
        nachCollumn.setCellValueFactory(new PropertyValueFactory<>("zielOrt"));
        startCollumn.setCellValueFactory(new PropertyValueFactory<>("startZeit"));
        ankunftCollumn.setCellValueFactory(new PropertyValueFactory<>("ankunftsZeit"));
        datumCollumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        preisCollumn.setCellValueFactory(new PropertyValueFactory<>("preis"));
        gepaeckCollumn.setCellValueFactory(new PropertyValueFactory<>("gepaeck"));
        personenCollumn.setCellValueFactory(new PropertyValueFactory<>("personenAnzahl"));

        buchungTabelView.setItems(wunschlistenEintragList);

    }

    @FXML
    private void handleBack(){
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }

    @FXML
    private void handleGo() {
        if (buchungTabelView.getSelectionModel().getSelectedItem() == null) {
            main.openMessageDialog("Keinen Flug ausgewählt");
        }else if(!buchungTabelView.getSelectionModel().getSelectedItem().isBuchbar()){
            main.openMessageDialog("Flug ist nicht mehr buchbar");
        }else {
            BuchungsuebersichtController.setHinflug(buchungTabelView.getSelectionModel().getSelectedItem());
            ((Anwender) main.benutzerprofil).entferneFlugVonWunschliste(buchungTabelView.getSelectionModel().getSelectedItem().getFlugId());
            main.selectView(ViewNavigation.BUCHUNGSUEBERSICHTSCENE);
        }
    }

    @FXML
    private void handleLoeschen(){
        FlugInformationClass selectedItem = buchungTabelView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            buchungTabelView.getItems().remove(selectedItem);
            ((Anwender) main.benutzerprofil).entferneFlugVonWunschliste(selectedItem.getFlugId());
        }
    }
}
