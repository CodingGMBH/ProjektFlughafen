package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Modell.FluegeSpeicher;
import It.fallmerayer.codingGmbH.projektFlughafen.Modell.Flughafen;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by gabriel on 17.04.17.
 */
//Finished
public class FlugAnsichtNurHinflugController extends AbstractController {
    @FXML Button zurueckButt;
    @FXML Button weiterButt;

    @FXML TableView<FlugInformationClass> hinflugTabelView;

    @FXML TableColumn<FlugInformationClass, String> vonCollumn;
    @FXML TableColumn<FlugInformationClass, String> nachCollumn;
    @FXML TableColumn<FlugInformationClass, String> startCollumn;
    @FXML TableColumn<FlugInformationClass, String> ankunftCollumn;
    @FXML TableColumn<FlugInformationClass, String> datumCollumn;
    @FXML TableColumn<FlugInformationClass, Double> preisCollumn;

    @FXML Label flugLabel;

    ObservableList<FlugInformationClass> flugInformationClassObservableList;

    @Override
    public void startController() {
        flugLabel.setText(HelpfullStrings.FLUGVONSTRING + main.hinflugInformation.getStartOrt() + HelpfullStrings.FLUGNACHSTRING + main.hinflugInformation.getZielOrt());

        flugInformationClassObservableList = FlugInformationClass.getObjektList(FluegeSpeicher.getInstance().getGefilterteFluege(new Flughafen(main.hinflugInformation.getStartOrt()), new Flughafen(main.hinflugInformation.getZielOrt()), LocalDateTime.parse(main.hinflugInformation.getDatum() + " 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), main.hinflugInformation.getPersonenAnzahl(), main.hinflugInformation.getGepaeck()));
        vonCollumn.setCellValueFactory(new PropertyValueFactory<>("startOrt"));
        nachCollumn.setCellValueFactory(new PropertyValueFactory<>("zielOrt"));
        startCollumn.setCellValueFactory(new PropertyValueFactory<>("startZeit"));
        ankunftCollumn.setCellValueFactory(new PropertyValueFactory<>("ankunftsZeit"));
        datumCollumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        preisCollumn.setCellValueFactory(new PropertyValueFactory<>("preis"));
        hinflugTabelView.setItems(flugInformationClassObservableList);
    }

    @FXML
    private void handleBack(){
        if (main.benutzerprofil == null) {
            main.selectView(ViewNavigation.STARTSCENE);
        } else {
            main.selectView(ViewNavigation.ANGEMELDETSCENE);
        }
    }

    @FXML
    private void handleWeiter(){
        if (hinflugTabelView.getSelectionModel().getSelectedItem() == null){
            main.openMessageDialog("Keinen Flug ausgewählt");
        } else {
            FlugInformationClass flugInformationClass = hinflugTabelView.getSelectionModel().getSelectedItem();
            flugInformationClass.setGepaeck(main.hinflugInformation.getGepaeck());
            flugInformationClass.setPersonenAnzahl(main.hinflugInformation.getPersonenAnzahl());
            BuchungsuebersichtController.setHinflug(flugInformationClass);
            BuchungsuebersichtController.setRueckflug(null);
            main.selectView(ViewNavigation.BUCHUNGSUEBERSICHTSCENE);
        }
    }
}
