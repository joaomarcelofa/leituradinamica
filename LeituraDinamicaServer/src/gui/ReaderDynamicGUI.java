package gui;

import client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import readerDynamic.ReaderDynamic;

public class ReaderDynamicGUI {
	
	private Stage janela;
	private MenuBar menuBar;
	private Menu menuConfig;
	private Menu menuFile;
	private Menu menuTime;
	private TextField timeTxt;
	private ComboBox<String> combo;
	private GridPane grid;
	private TextField visor;
	private Button bttPlay;
	private Button bttPause;
	private Button bttStop;
	private HBox bttContainer;
	
	private String message = "";
	private String[] files;
	private int tempo;
	
	private Client client;
	private ReaderDynamic rd;
	
	public void createView(Stage janela){
		this.janela = janela;
		createGUI();
		createConnectionGUI();
	}
	
	private void createGUI(){
		VBox painel = new VBox();
		BorderPane border = new BorderPane();
		border.setCenter(painel);
		Scene cena = new Scene(border, 350, 130);
		painel.setSpacing(10.0);
		painel.getChildren().addAll(createMenuBar(new MenuBar()), createPanel(new GridPane()));
		this.janela.setScene(cena);
		this.janela.setTitle("Leitura Dinâmica");
		this.janela.show();
	}
	
	private void createConnectionGUI(){
		client = new Client();
		client.createConnection();
		loadComboBox();
	}
	
	private MenuBar createMenuBar(MenuBar menuBar){
		this.menuBar = menuBar;
		menuConfig = new Menu("Config");
		menuConfig.getItems().addAll(createMenuFile(), createMenuTime());
		this.menuBar.getMenus().addAll(menuConfig);
		return this.menuBar;
	}
	
	private CustomMenuItem createCustomComboBox(ComboBox<String> combo){
		CustomMenuItem customMenuFileItem = new CustomMenuItem(combo);
		customMenuFileItem.setHideOnClick(false);
		return customMenuFileItem;
	}
	
	private ComboBox<String> createComboBox(ComboBox<String> combo){
		this.combo = combo;
		this.combo.setPromptText("Escolha um arquivo");
		this.combo.setOnAction(e->{
			onClickComboBox(this.combo);
		});
		return this.combo;
	}
	
	private void onClickComboBox(ComboBox<String> combo){
		message = files[combo.getSelectionModel().getSelectedIndex()];
		String str = client.request(message);
	}
	
	private void loadComboBox(){
		files = client.request("begin").split(";");
		this.combo.getItems().addAll(files);
	}
	
	private Menu createMenuFile(){
		menuFile = new Menu("Search File");
		menuFile.getItems().add(createCustomComboBox(createComboBox(new ComboBox<String>())));
		return menuFile;
	}
	
	private CustomMenuItem createCustomTextField(TextField txt){
		CustomMenuItem customMenuFileItem = new CustomMenuItem(txt);
		customMenuFileItem.setHideOnClick(false);
		return customMenuFileItem;
	}
	
	private TextField createTextField(){
		timeTxt = new TextField("em milisegundos");
		timeTxt.setOnMouseClicked(e->{
			onMouseClicked(timeTxt);
		});
		timeTxt.setOnKeyPressed(e->{
			if(e.getCode().toString() == "ENTER"){
				onKeyPressedTextfield();
			}
		});
		return timeTxt;
	}
	
	private void onKeyPressedTextfield(){
		if (message == null){
			onMouseClicked(this.timeTxt);
		}
		else{
			menuConfig.hide();
			rd = new ReaderDynamic(client, visor);
			rd.setTime(timeTxt.getText());
			rd.setConfigsReaderDynamic();
		}
	}
	
	private void onMouseClicked(TextField txt){
		txt.setText("");
	}
	
	private Menu createMenuTime(){
		menuTime = new Menu("Insert Time");
		menuTime.getItems().add(createCustomTextField(createTextField()));
		return menuTime;
	}

	private GridPane createPanel(GridPane grid){
		this.grid = grid;
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(3);
		this.grid.setVgap(5);
		this.grid.add(createVisor(new TextField()), 1, 1);
		this.grid.add(createButtons(new HBox()), 1, 2);
		return this.grid;
	}
	
	private TextField createVisor(TextField visor){
		this.visor = visor;
		this.visor.setPrefSize(200, 30);
		this.visor.setStyle("-fx-text-alignment:left;-fx-text-fill: black");
		this.visor.setAlignment(Pos.CENTER_RIGHT);
		this.visor.setFont(Font.font(18));
		return this.visor;
	}

	private HBox createButtons(HBox bttContainer){
		this.bttContainer = bttContainer;
		
		this.bttPlay = new Button("PLAY");
		this.bttPlay.setMinWidth(70.0);
		this.bttPlay.setOnAction(e->{
			rd.setTimelinePlay();
			bttPlay.setText("PLAY");
		});
		
		this.bttPause = new Button("PAUSE");
		this.bttPause.setMinWidth(65.0);
		this.bttPause.setOnAction(e->{
			rd.setTimelinePause();
			if (bttPlay.getText().equals("PLAY")){
				bttPlay.setText("REINICIAR");
			}
		});
		
		this.bttStop = new Button("STOP");
		this.bttStop.setMinWidth(65.0);	
		this.bttStop.setOnAction(e-> {
			this.visor.setText(client.request("stop"));
			rd.setTimelineStop();
			bttPlay.setText("PLAY");
		});
		
		this.bttContainer.setSpacing(10.0);
		this.bttContainer.getChildren().addAll(bttPlay, bttPause, bttStop);
		return this.bttContainer;
	}
	
	public void closeView(){
		System.out.println(client.request("close"));

		client.closeConnection();
	}
}
