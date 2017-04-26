package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.Modell.FluegeSpeicher;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.Flughafen;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.FlugInformationClass;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.HelpfullStrings;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
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
public class FlugAnsichtController extends AbstractController {
    @FXML Button zurueckButt;
    @FXML Button weiterButt;

    @FXML TableView<FlugInformationClass> hinflugTabelView;

    @FXML TableColumn<FlugInformationClass, String> vonHinCollumn;
    @FXML TableColumn<FlugInformationClass, String> nachHinCollumn;
    @FXML TableColumn<FlugInformationClass, String> startHinCollumn;
    @FXML TableColumn<FlugInformationClass, String> ankunftHinCollumn;
    @FXML TableColumn<FlugInformationClass, String> datumHinCollumn;
    @FXML TableColumn<FlugInformationClass, Double> preisHinCollumn;

    @FXML TableView<FlugInformationClass> rueckflugTabelView;

    @FXML TableColumn<FlugInformationClass, String> vonRueckCollumn;
    @FXML TableColumn<FlugInformationClass, String> nachRueckCollumn;
    @FXML TableColumn<FlugInformationClass, String> startRueckCollumn;
    @FXML TableColumn<FlugInformationClass, String> ankunftRueckCollumn;
    @FXML TableColumn<FlugInformationClass, String> datumRueckCollumn;
    @FXML TableColumn<FlugInformationClass, Double> preisRueckCollumn;

    @FXML Label flugLabel;

    ObservableList<FlugInformationClass> hinflugInformationClassObservableList;
    ObservableList<FlugInformationClass> rueckflugInformationClassObservableList;

    @Override
    public void startController() {
        flugLabel.setText(HelpfullStrings.FLUGVONSTRING + main.hinflugInformation.getStartOrt() + HelpfullStrings.FLUGNACHSTRING + main.hinflugInformation.getZielOrt());

        hinflugInformationClassObservableList = FlugInformationClass.getObjektList(FluegeSpeicher.getInstance().getGefilterteFluege(new Flughafen(main.hinflugInformation.getStartOrt()), new Flughafen(main.hinflugInformation.getZielOrt()), LocalDateTime.parse(main.hinflugInformation.getDatum() + " 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), main.hinflugInformation.getPersonenAnzahl(), main.hinflugInformation.getGepaeck()));
        vonHinCollumn.setCellValueFactory(new PropertyValueFactory<>("startOrt"));
        nachHinCollumn.setCellValueFactory(new PropertyValueFactory<>("zielOrt"));
        startHinCollumn.setCellValueFactory(new PropertyValueFactory<>("startZeit"));
        ankunftHinCollumn.setCellValueFactory(new PropertyValueFactory<>("ankunftsZeit"));
        datumHinCollumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        preisHinCollumn.setCellValueFactory(new PropertyValueFactory<>("preis"));
        hinflugTabelView.setItems(hinflugInformationClassObservableList);

        rueckflugInformationClassObservableList = FlugInformationClass.getObjektList(FluegeSpeicher.getInstance().getGefilterteFluege(new Flughafen(main.rueckflugInformation.getStartOrt()), new Flughafen(main.rueckflugInformation.getZielOrt()), LocalDateTime.parse(main.rueckflugInformation.getDatum() + " 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), main.rueckflugInformation.getPersonenAnzahl(), main.rueckflugInformation.getGepaeck()));
        vonRueckCollumn.setCellValueFactory(new PropertyValueFactory<>("startOrt"));
        nachRueckCollumn.setCellValueFactory(new PropertyValueFactory<>("zielOrt"));
        startRueckCollumn.setCellValueFactory(new PropertyValueFactory<>("startZeit"));
        ankunftRueckCollumn.setCellValueFactory(new PropertyValueFactory<>("ankunftsZeit"));
        datumRueckCollumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        preisRueckCollumn.setCellValueFactory(new PropertyValueFactory<>("preis"));
        rueckflugTabelView.setItems(hinflugInformationClassObservableList);
    }

    @FXML
    private void handleBack(){
        if (main.benutzerprofil == null){
            main.selectView(ViewNavigation.STARTSCENE);
        }else{
            main.selectView(ViewNavigation.ANGEMELDETSCENE);
        }
    }

    @FXML
    private void handleWeiter(){
        if (hinflugTabelView.getSelectionModel().getSelectedItem() == null && rueckflugTabelView.getSelectionModel().getSelectedItem() == null){
            main.openMessageDialog("Keinen Flug ausgewählt");
        } else if (rueckflugTabelView.getSelectionModel().getSelectedItem() == null) {
            BuchungsuebersichtController.setHinflug(hinflugTabelView.getSelectionModel().getSelectedItem());
            BuchungsuebersichtController.setRueckflug(null);
            main.selectView(ViewNavigation.BUCHUNGSUEBERSICHTSCENE);
        } else {
            BuchungsuebersichtController.setHinflug(hinflugTabelView.getSelectionModel().getSelectedItem());
            BuchungsuebersichtController.setRueckflug(rueckflugTabelView.getSelectionModel().getSelectedItem());
            main.selectView(ViewNavigation.BUCHUNGSUEBERSICHTSCENE);
        }
    }
}
