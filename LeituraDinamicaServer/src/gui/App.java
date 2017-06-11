package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	Screen view;
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage janela) throws Exception {
		view = new Screen();
		view.createView(janela);
	}
	
	//Closing the connection
	@Override
	public void stop(){
		System.out.println("saindo!!");
		view.closeView();
	}

}

