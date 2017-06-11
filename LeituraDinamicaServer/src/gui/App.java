package gui;

import componenets.GUIComponents;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	
	GUIComponents gui;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage janela) throws Exception {
		this.gui       = new GUIComponents();
		BorderPane GUI = this.gui.getGUI();
		Scene cena     = new Scene(GUI, 350, 130);
		
		janela.setScene(cena);
		janela.setTitle("Leitura Dinamica");
		janela.show();
	}
	
	@Override
	public void stop(){
		System.out.println("saindo!!");
		//view.closeView();
	}

}

