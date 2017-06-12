package gui;

import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

public class MenuManager {
/*
	private MenuBar menuBar;
	private Menu 	menuConfig;
	private Menu 	menuFile;
	private Menu 	menuTime;
	
	private EventManager manager;
	
	public MenuManager(EventManager manager) {
		super();
		this.manager = manager;
		this.manager.setMenu(this);
	}
	
	public Menu getMenuFile(){
		return this.menuFile;
	}
	
	public Menu getMenuTime(){
		return this.menuTime;
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
		combo.setPromptText("Escolha um arquivo");
		combo.setOnAction(e->{
			manager.onClickComboBox(combo);
		});
		combo.getItems().addAll(manager.loadComboBox());
		return combo;
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
	
	private TextField createTextField(TextField timeTxt){
		timeTxt.setOnMouseClicked(e->{
			manager.onMouseClicked(timeTxt);
		});
		timeTxt.setOnKeyPressed(e->{
			if(e.getCode().toString() == "ENTER"){
				manager.onKeyPressedTextfield(menuConfig, timeTxt);
			}
		});
		return timeTxt;
	}
	
	private Menu createMenuTime(){
		menuTime = new Menu("Insert Time");
		menuTime.setDisable(true);
		menuTime.getItems().add(createCustomTextField(createTextField(new TextField("em milisegundos"))));
		return menuTime;
	}
	*/
}