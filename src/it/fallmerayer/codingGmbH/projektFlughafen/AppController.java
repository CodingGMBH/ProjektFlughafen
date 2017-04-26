package it.fallmerayer.codingGmbH.projektFlughafen;

/**
 * Created by gabriel on 17.04.17.
 */

import it.fallmerayer.codingGmbH.projektFlughafen.Controller.*;
import it.fallmerayer.codingGmbH.projektFlughafen.Modell.*;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.FlugInformationClass;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.MainApp;
import it.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AppController {

    protected Stage primaryStage;
    protected BorderPane rootLayout;
    public String lastController;
    public String currentController;

    public Benutzerprofil benutzerprofil;
    public FlugInformationClass hinflugInformation;
    public FlugInformationClass rueckflugInformation;

    public AppController(Stage primaryStage, boolean stardet) {
        super();
        this.primaryStage = primaryStage;
        if (!stardet){
            openMessageDialog("The System didn't start properly");
        }
    }

    public void startController() {
        initLoginView();
    }

    /**
     * Initializes the root layout.
     */
    public void initLoginView() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(ViewNavigation.STARTSCENE));
            AnchorPane loginView = loader.load();

            lastController = ViewNavigation.STARTSCENE;
            currentController = ViewNavigation.STARTSCENE;
            // Give the controller access to the main app.
            StartSceneController controller = loader.getController();
            controller.setMainApp(this);
            controller.startController();

            // Show the scene containing the root layout.
            Scene scene = new Scene(loginView);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectView(String viewPath) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(viewPath));
            AnchorPane view = loader.load();

            setControllerPath(viewPath);

            // Give the controller access to the main app.
            AbstractController controller = loader.getController();
            controller.setMainApp(this);
            controller.startController();

            // Show the scene containing the root layout.
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openGepaeckDialog(String flug) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(ViewNavigation.INPUTBOXGEPAECKDIALOG));
            GridPane view = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Gepäck");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(view);
            dialogStage.setScene(scene);

            // Set the person into the controller
            InputBoxGepaeckDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFlug(flug);
            controller.setMainApp(this);
            controller.startController();

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openPersonDialpg(String person, int howMany) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(ViewNavigation.INPUTBOXPERSONDIALOG));
            GridPane view = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(view);
            dialogStage.setScene(scene);

            // Set the person into the controller
            InputBoxPersonDialogController controller = loader.getController();
            controller.setHowOften(howMany);
            controller.setPerson(person);
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.startController();

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openMessageDialog(String message) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(ViewNavigation.MESSAGEDIALOG));
            GridPane view = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Achtung!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(view);
            dialogStage.setScene(scene);

            // Set the person into the controller
            MessageDialogController controller = loader.getController();
            controller.setMessage(message);
            controller.setDialogStage(dialogStage);
            controller.startController();
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setControllerPath(String viewPath){
        if (lastController.matches(currentController)){
            currentController = viewPath;
        }else{
            lastController = currentController;
            currentController = viewPath;
        }
    }

}
