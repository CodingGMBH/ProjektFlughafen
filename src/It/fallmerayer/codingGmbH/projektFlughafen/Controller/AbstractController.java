package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.AppController;

public class AbstractController {

	protected AppController main;

	public void setMainApp(AppController main){
		this.main = main;
	}

	public void startController(){}

}