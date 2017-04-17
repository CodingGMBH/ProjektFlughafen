package Flughafen.Controller;

import Flughafen.AppController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class AbstractController {

    //TODO inject this properties
	protected AppController main;

	public void setMainApp(AppController main){
		this.main = main;
	}

}