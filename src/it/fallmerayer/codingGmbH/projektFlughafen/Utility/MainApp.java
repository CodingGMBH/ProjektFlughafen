package it.fallmerayer.codingGmbH.projektFlughafen.Utility;

import it.fallmerayer.codingGmbH.projektFlughafen.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flughafen");
        initApp(primaryStage);
    }
    
    private void initApp(Stage primaryStage) {
		initConfiguration();
		initController(primaryStage);
	}

	private void initController(Stage primaryStage) {
		AppController controller = new AppController(primaryStage);
		controller.startController();
	}

	private void initConfiguration() {
		
	}

}