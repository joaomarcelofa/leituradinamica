package gui;

import componenets.GUIComponents;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	GUIComponents gui;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage janela) throws Exception {
		GUIController controller = new GUIController();
		controller.launchGUI(janela);
	}
	
	@Override
	public void stop(){
		System.out.println("saindo!!");
	}

}

