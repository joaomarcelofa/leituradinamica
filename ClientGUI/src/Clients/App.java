package Clients;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage janela) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("oooi");
		ReaderDynamicGUI view = new ReaderDynamicGUI();
		view.createView(janela);
	}
	
	//close connection - not file
	
	

}

