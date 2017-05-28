package Clients;

<<<<<<< HEAD
import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
=======
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
>>>>>>> master
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
<<<<<<< HEAD
import javafx.scene.shape.Path;
=======
>>>>>>> master
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReaderDynamicGUI {
	
	private Stage janela;
	private MenuBar menuBar;
	private Menu menuFile;
	private Menu menuTime;
	private TextField timeTxt;
	private TextField fileTxt;
	private GridPane grid;
	private TextField visor;
	private Button bttPlay;
	private Button bttPause;
	private Button bttStop;
	private HBox bttContainer;
	
	
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
		Menu menuConfig = new Menu("Config");
		menuConfig.getItems().addAll(createMenuFile(), createMenuTime());
		this.menuBar.getMenus().addAll(menuConfig);
		return this.menuBar;
	}
	
<<<<<<< HEAD
	private CustomMenuItem createCustomMenuItem(ComboBox combo){
		CustomMenuItem customMenuFileItem = new CustomMenuItem(combo);
=======
	private CustomMenuItem createCustomMenuItem(TextField txt){
		CustomMenuItem customMenuFileItem = new CustomMenuItem(txt);
>>>>>>> master
		customMenuFileItem.setHideOnClick(false);
		return customMenuFileItem;
	}
	
	//clear the text time when the mouse clicks on it
	private void onMouseClicked(TextField txt){
		txt.setText("");	
	}
	
<<<<<<< HEAD
	private void readFilesPath(){
		String pathh = searchFile();
		//Path path = new Path();
		//System.out.println("a pathh �: " + pathh);
		
		File directory = new File(pathh);
        //get all the files from a directory
		String[] names = directory.list();
	    File[] fList = directory.listFiles();
	    String tam = "" + names.length;
	    System.out.println("tamanho: " + tam);
	    /*for (File file : fList){
	        if (file.isFile()){
	            System.out.println(file.getName());
	        }
	    }*/		
	}
	

	
	
	private String searchFile(){
		String path = System.getProperty("user.dir");
		path += "\\Texts";
		path = formatPath(path);
		return path;
	}
	
	private String formatPath(String filePath){
		String newPath = filePath;
		char barra = '\'';
		String novo = System.getProperty("file.separator");
		newPath.replace(barra,novo.charAt(0));
		return newPath;
	}
	
	private Menu createMenuFile(){
		
		menuFile = new Menu("Search File");
		ComboBox fileCombo = new ComboBox();
		
		readFilesPath();
		
		fileCombo.getItems().addAll(
	            "jacob.smith@example.com",
	            "isabella.johnson@example.com",
	            "ethan.williams@example.com",
	            "emma.jones@example.com",
	            "michael.brown@example.com");
		
		fileTxt = new TextField("nome do arquivo");
		
		
		
		menuFile.getItems().add(createCustomMenuItem(fileCombo));
=======
	private Menu createMenuFile(){
		
		menuFile = new Menu("Search File");
		fileTxt = new TextField("nome do arquivo");
		menuFile.getItems().add(createCustomMenuItem(fileTxt));
>>>>>>> master
		
		fileTxt.setOnMouseClicked(e->{
			onMouseClicked(fileTxt);
		});
		
		//when the enter is pressed, the value of time is submit
		fileTxt.setOnKeyPressed(e->{
			/*if(e.getCode().toString() == "ENTER"){
				onValidateTime(timeTxt.getText());
				rd.criarAnimacao(words, tempo, visor);
				menuConfig.hide();
			}*/
		});
		return menuFile;
	}
	
	private Menu createMenuTime(){
		menuTime = new Menu("Insert Time");
		timeTxt = new TextField("em milisegundos");
		
<<<<<<< HEAD
		//menuTime.getItems().add(createCustomMenuItem(timeTxt));
=======
		menuTime.getItems().add(createCustomMenuItem(timeTxt));
>>>>>>> master
		
		timeTxt.setOnMouseClicked(e->{ 
			onMouseClicked(timeTxt);
		});
		
		//when the enter is pressed, the value of time is submit
		timeTxt.setOnKeyPressed(e->{
			/*if(e.getCode().toString() == "ENTER"){
				onValidateTime(timeTxt.getText());
				rd.criarAnimacao(words, tempo, visor);
				menuConfig.hide();
			}*/
		});
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
