package gui;

import client.Client;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import readerDynamic.ReaderDynamic;


public class ReaderDynamicGUI {
	
	private Stage janela;
	
	private Client client;
	private ReaderDynamic rd;
	
	
	// -------------------- CONSTRUTOR --------------------
	
	public void createView(Stage janela){
		this.janela = janela;
		createConnectionGUI();
		rd = new ReaderDynamic(client);
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
		MenuConfig menu = new MenuConfig(client, rd);
		return menu.createMenuBar(new MenuBar());
	}
	
	private GridPane createPanel(){
		Panel panel = new Panel(client, rd);
		return panel.createPanel(new GridPane());
	}
	
	
	// -------------------- CONNECTION METHODS --------------------
	
	private void createConnectionGUI(){
		client = new Client();
		client.createConnection();
	}
	
	public void closeView(){
		System.out.println(client.request("close"));
		client.closeConnection();
	}
	
}
