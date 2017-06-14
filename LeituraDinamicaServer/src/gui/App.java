package gui;

import componenets.GUIComponents;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	GUIComponents gui;
	GUIController controller;
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage janela) throws Exception {
		this.controller = new GUIController();
		this.controller.launchGUI(janela);
	}
	
	@Override
	public void stop(){
		this.controller.closeGUI();
	}

}
