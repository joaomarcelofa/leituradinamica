package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage janela) throws Exception {
		System.out.println("oooi");
		ReaderDynamicGUI view = new ReaderDynamicGUI();
		view.createView(janela);
	}
	
	//close connection - not file
	
	

}

