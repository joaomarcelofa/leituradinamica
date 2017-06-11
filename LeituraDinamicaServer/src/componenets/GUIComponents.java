package componenets;

import javafx.geometry.Pos;
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

public class GUIComponents {

	private MenuBar menuBar;
	
	private Menu menuConfig;
	private Menu menuFile;
	private Menu menuTime;
	
	private ComboBox<String> combo;
	
	private GridPane grid;
	
	private TextField visor;
	private TextField speed;
	
	private Button bttPlay;
	private Button bttPause;
	private Button bttStop;

	private BorderPane gui;
	
	public BorderPane getGUI() {
		mountGraphicInterface();
		return this.gui;
	}
	
	public void mountGraphicInterface() {
		mountGrid();
		mountMenu();
		createGUI();
	}
	
	private void createGUI(){
		VBox painel = new VBox();
		this.gui = new BorderPane();
		
		this.gui.setCenter(painel);
		
		painel.setSpacing(10.0);
		painel.getChildren().addAll(this.menuBar , this.grid);
	}
	
	// -------------------- Getters --------------------
	
	public MenuBar getMenuBar() {
		return menuBar;
	}

	public Menu getMenuConfig() {
		return menuConfig;
	}

	public Menu getMenuFile() {
		return menuFile;
	}

	public Menu getMenuTime() {
		return menuTime;
	}

	public ComboBox<String> getCombo() {
		return combo;
	}

	public TextField getVisor() {
		return visor;
	}

	public TextField getSpeed() {
		return speed;
	}

	public Button getBttPlay() {
		return bttPlay;
	}

	public Button getBttPause() {
		return bttPause;
	}

	public Button getBttStop() {
		return bttStop;
	}
	
	// -------------------- Grid Factory --------------------

	private void mountGrid() {
		configGrid();
		configVisor();
		configButtons();
		this.grid.add(mountVisor(), 1, 1);		
		this.grid.add(mountHBox(), 1, 2);
	}
	
	private void configGrid() {
		this.grid = new GridPane();
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(3);
		this.grid.setVgap(5);
	}
	
	// -------------------- TextField Factory (GridPane)--------------------
	
	private void configVisor() {
		this.visor = new TextField();
		this.visor.setPrefSize(200, 30);
		this.visor.setAlignment(Pos.CENTER_RIGHT);
		this.visor.setEditable(false);
	}
	
	private TextField mountVisor() {
		return this.visor;
	}
	
	// -------------------- Button Factory --------------------
	
	private HBox mountHBox() {
		HBox bttContainer = new HBox();
		bttContainer.setSpacing(10.0);
		bttContainer.getChildren().addAll(this.bttPlay, this.bttPause, this.bttStop);
		return bttContainer;
	}
	
	private void configButtons() {
		this.bttPlay 	= createButton("Play",70.0);
		this.bttPause	= createButton("Pause",70.0);
		this.bttStop	= createButton("Stop", 70.0);
	}
	
	private Button createButton(String name, double size) {
		Button btt = new Button(name);
		btt.setMinWidth(size);
		return btt;
	}
	
	// -------------------- Menu Factory --------------------
	
	private void mountMenu() {
		createMenuFile();
		createMenuTime();
		configMenuBar();
	}
	
	private void configMenuBar() {
		this.menuConfig = new Menu("Configuração");
		this.menuConfig.getItems().addAll(this.menuFile,this.menuTime);
		this.menuBar = new MenuBar();
		this.menuBar.getMenus().addAll(this.menuConfig);	
	}
	
	private void createMenuFile(){
		this.menuFile = new Menu("Search File");
		this.menuFile.getItems().add(createCustomComboBox());
	}
	
	private CustomMenuItem createCustomComboBox(){
		CustomMenuItem customMenuFileItem = new CustomMenuItem(createComboBox());
		customMenuFileItem.setHideOnClick(false);
		return customMenuFileItem;
	}
	
	private ComboBox<String> createComboBox(){
		this.combo = new ComboBox<String>();
		this.combo.setPromptText("Escolha um arquivo");
		return combo;
	}
	
	private void createMenuTime() {
		this.menuTime = new Menu("Insert Time");
		configTimeTextField();
		this.menuTime.getItems().add(createCustomTextField());
	}
	
	private CustomMenuItem createCustomTextField(){
		CustomMenuItem customMenuFileItem = new CustomMenuItem(this.speed);
		customMenuFileItem.setHideOnClick(false);
		return customMenuFileItem;
	}
	
	private void configTimeTextField() {
		this.speed = new TextField("Em milisegundos");
	}
}