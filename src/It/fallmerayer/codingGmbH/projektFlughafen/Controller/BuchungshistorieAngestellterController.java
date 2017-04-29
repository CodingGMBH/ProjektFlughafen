package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Model.BuchungsprofileSpeicher;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.BuchungInformationClass;
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
public class BuchungshistorieAngestellterController extends AbstractController {
    @FXML Button backButt;
    @FXML Button goButt;

    @FXML TableView<BuchungInformationClass> buchungTabelView;

    @FXML Label angemeldetLabel;

    @FXML TableColumn<BuchungInformationClass, String> vonCollumn;
    @FXML TableColumn<BuchungInformationClass, String> nachCollumn;
    @FXML TableColumn<BuchungInformationClass, String> startCollumn;
    @FXML TableColumn<BuchungInformationClass, String> ankunftCollumn;
    @FXML TableColumn<BuchungInformationClass, String> datumCollumn;
    @FXML TableColumn<BuchungInformationClass, Double> preisCollumn;
    @FXML TableColumn<BuchungInformationClass, Double> gepaeckCollumn;
    @FXML TableColumn<BuchungInformationClass, Integer> personenCollumn;
    @FXML TableColumn<BuchungInformationClass, String> gebuchtFuerCollumn;

    private ObservableList<BuchungInformationClass> buchungsprofilObservableList;

    @Override
    public void startController() {
        angemeldetLabel.setText(HelpfullStrings.ANGEMELDETALSSTRING + main.benutzerprofil.getBenutzerName());
        buchungsprofilObservableList = BuchungInformationClass.getObjektListAngestellter(BuchungsprofileSpeicher.getInstance().getBuchungsprofile(main.benutzerprofil.getPID()));
        vonCollumn.setCellValueFactory(new PropertyValueFactory<>("startOrt"));
        nachCollumn.setCellValueFactory(new PropertyValueFactory<>("zielOrt"));
        startCollumn.setCellValueFactory(new PropertyValueFactory<>("startZeit"));
        ankunftCollumn.setCellValueFactory(new PropertyValueFactory<>("ankunftsZeit"));
        datumCollumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        preisCollumn.setCellValueFactory(new PropertyValueFactory<>("preis"));
        gepaeckCollumn.setCellValueFactory(new PropertyValueFactory<>("gepaeck"));
        personenCollumn.setCellValueFactory(new PropertyValueFactory<>("personenAnzahl"));
        gebuchtFuerCollumn.setCellValueFactory(new PropertyValueFactory<>("gebuchtFuer"));
        buchungTabelView.setItems(buchungsprofilObservableList);
    }

    @FXML
    private void handleBack(){
        main.selectView(ViewNavigation.ANGEMELDETSCENE);
    }

    @FXML
    private void handleGo(){
        BuchungInformationClass selectedItem = buchungTabelView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            FlugEinsehenController.setBuchungshistorie(selectedItem);
            main.selectView(ViewNavigation.FLUGEINSEHENSCENE);
        } else {
            main.openMessageDialog("Es wurde kein Flug ausgewählt");
        }
    }
}
