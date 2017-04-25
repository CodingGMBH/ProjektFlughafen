package it.fallmerayer.codingGmbH.projektFlughafen.Controller;

import it.fallmerayer.codingGmbH.projektFlughafen.AppController;

public class AbstractController {

    //TODO inject this properties
	protected AppController main;

	public void setMainApp(AppController main){
		this.main = main;
	}

	public void startController(){}

}