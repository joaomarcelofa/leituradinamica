package gui;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ReaderDynamicGUI {
	
	private Stage janela;
	
	private ControllerGUI controller;
	
	// -------------------- CONSTRUTOR --------------------
	
	public void createView(Stage janela){
		this.janela = janela;
		
		this.controller = new ControllerGUI();
		this.controller.createConnectionGUI();
		this.controller.createReaderDdynamic();
		
		createGUI();
	}
	
	// -------------------- GUI METHODS --------------------
	
	private void createGUI(){
		VBox painel = new VBox();
		BorderPane border = new BorderPane();
		border.setCenter(painel);
		Scene cena = new Scene(border, 350, 130);
		painel.setSpacing(10.0);
		painel.getChildren().addAll(createMenuConfig(), createPanel());
		this.janela.setScene(cena);
		this.janela.setTitle("Leitura Dinâmica");
		this.janela.show();
	}
	
	private MenuBar createMenuConfig(){
		MenuConfig menu = new MenuConfig(controller);
		return menu.createMenuBar(new MenuBar());
	}
	
	private GridPane createPanel(){
		Panel panel = new Panel(controller);
		return panel.createPanel(new GridPane());
	}
		
	public void closeView(){
		this.controller.closeView();
	}
	
}
