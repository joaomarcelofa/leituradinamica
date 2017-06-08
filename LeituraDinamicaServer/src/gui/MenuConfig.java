package gui;

import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

public class MenuConfig {

	private MenuBar menuBar;
	private Menu menuConfig;
	private Menu menuFile;
	private Menu menuTime;
	private TextField timeTxt;
	private ComboBox<String> combo;
	
	
	private ControllerGUI controller;
	
	public MenuConfig(ControllerGUI controller) {
		super();
		this.controller = controller;
	}


	// -------------------- GUI METHODS --------------------
	
	public MenuBar createMenuBar(MenuBar menuBar){
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
			controller.onClickComboBox(this.combo);
		});
		combo.getItems().addAll(controller.loadComboBox());
		return this.combo;
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
			controller.onMouseClicked(timeTxt);
		});
		
		timeTxt.setOnKeyPressed(e->{
			if(e.getCode().toString() == "ENTER"){
				controller.onKeyPressedTextfield(menuConfig, timeTxt.getText());
			}
		});
		return timeTxt;
	}
	
	private Menu createMenuTime(){
		menuTime = new Menu("Insert Time");
		menuTime.getItems().add(createCustomTextField(createTextField()));
		return menuTime;
	}
	
}
