package Clients;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import File.Reader;
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
	
	private String message;
	private List<String> files;
	
	public void createView(Stage janela){
		this.janela = janela;
		
		VBox painel = new VBox();
		BorderPane border = new BorderPane();
		
		border.setCenter(painel);
		Scene cena = new Scene(border, 350, 130);
	
		painel.setSpacing(10.0);
		painel.getChildren().addAll(createMenuBar(new MenuBar()), createPanel(new GridPane()));
		
		this.janela.setScene(cena);
		this.janela.setTitle("Leitura Din�mica");
		this.janela.show();
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
		this.combo.getItems().addAll(searchFiles());
		this.combo.setOnAction(e->{
			onClickComboBox(this.combo);
		});
		return this.combo;
	}
	
	private void onClickComboBox(ComboBox<String> combo){
		message = files.get(combo.getSelectionModel().getSelectedIndex());
	}
	
	private List<String> searchFiles(){
		files = new ArrayList<String>();
		Reader rd = new Reader();
		String path = rd.searchFile("");
		File[] listfiles = new File(path).listFiles();
		for (File file : listfiles) {
		    if (file.isFile()) {
		        files.add(file.getName());
		    }
		}		
		return files;
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
			message += ";" + timeTxt.getText();
			menuConfig.hide();
			//ClientSocket client = new ClientSocket();
			//client.enviarDados("TESTANDO!!!");
			//send the message to server 
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
		
		//create three buttons, play, pause and stop with event on each
		this.bttPlay = new Button("PLAY");
		this.bttPlay.setMinWidth(70.0);
		this.bttPlay.setOnAction(e->{
			//onPlayButton();
		});
		
		this.bttPause = new Button("PAUSE");
		this.bttPause.setMinWidth(65.0);
		this.bttPause.setOnAction(e->{
			//onPauseButton();
		});
		
		this.bttStop = new Button("STOP");
		this.bttStop.setMinWidth(65.0);	
		this.bttStop.setOnAction(e-> {
			//onStopButton();
		});
		
		this.bttContainer.setSpacing(10.0);
		this.bttContainer.getChildren().addAll(bttPlay, bttPause, bttStop);
		return this.bttContainer;
	}

}
