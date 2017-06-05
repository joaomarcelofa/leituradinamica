package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	ReaderDynamicGUI view;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage janela) throws Exception {
		view = new ReaderDynamicGUI();
		view.createView(janela);
	}
	
	//close connection - not file

	public void stop(){
		System.out.println("saindo!!");
		view.closeView();
	}
	

}

