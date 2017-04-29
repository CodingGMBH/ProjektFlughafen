package It.fallmerayer.codingGmbH.projektFlughafen.Utility;

import It.fallmerayer.codingGmbH.projektFlughafen.AppController;
import It.fallmerayer.codingGmbH.projektFlughafen.Modell.BenutzerprofilSpeicher;
import It.fallmerayer.codingGmbH.projektFlughafen.Modell.BuchungsprofileSpeicher;
import It.fallmerayer.codingGmbH.projektFlughafen.Modell.FluegeSpeicher;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
	BuchungsprofileSpeicher buchungsprofileSpeicher;
	BenutzerprofilSpeicher benutzerprofilSpeicher;
	FluegeSpeicher fluegeSpeicher;
	Boolean stardet = true;

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
		AppController controller = new AppController(primaryStage, stardet);
		if (stardet){
			controller.startController();
		}
	}

	private void initConfiguration() {
		fluegeSpeicher = FluegeSpeicher.getInstance();
		buchungsprofileSpeicher = BuchungsprofileSpeicher.getInstance();
		benutzerprofilSpeicher = BenutzerprofilSpeicher.getInstance();
		try {
			fluegeSpeicher.ausDateiLesen();
			fluegeSpeicher.fluegeInSystemEingliedern();
			buchungsprofileSpeicher.ausDateiLesen();
			benutzerprofilSpeicher.ausDateiLesen();
		} catch (IOException e) {
			stardet = false;
		}
	}

	@Override
	public void stop(){
		try {
			fluegeSpeicher.inDateiSchreiben();
			buchungsprofileSpeicher.inDateiSchreiebn();
			benutzerprofilSpeicher.inDateiSchreiben();
		} catch (IOException e) {
			System.out.println("Programm konnte nicht richtig geschlossen werden");
		}
	}
}