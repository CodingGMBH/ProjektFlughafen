package Flughafen;

/**
 * Created by gabriel on 17.04.17.
 */

import Flughafen.Controller.AbstractController;
import Flughafen.Controller.StartSceneController;
import Flughafen.Utility.MainApp;
import Flughafen.Utility.ViewNavigation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {

    protected Stage primaryStage;
    protected BorderPane rootLayout;

    public AppController(Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;
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

            // Give the controller access to the main app.
            StartSceneController controller = loader.getController();
            controller.setMainApp(this);

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

            // Give the controller access to the main app.
            AbstractController controller = loader.getController();
            controller.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
