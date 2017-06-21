package gui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe que inicia e fecha a aplica��o. A partir dessa classe, � instanciada a classe 
 * GUIController(), respons�vel pelo controle da aplica��o. 
 *
 */
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
