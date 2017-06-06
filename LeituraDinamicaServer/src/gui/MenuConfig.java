package gui;

import client.Client;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import readerDynamic.ReaderDynamic;

public class MenuConfig {

	private MenuBar menuBar;
	private Menu menuConfig;
	private Menu menuFile;
	private Menu menuTime;
	private TextField timeTxt;
	private ComboBox<String> combo;
	
	private String message = "";
	private String[] files;
	
	private Client client;
	private ReaderDynamic rd;
	
	
	public MenuConfig(Client client, ReaderDynamic rd) {
		super();
		this.client = client;
		this.rd = rd;
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
			onClickComboBox(this.combo);
		});
		loadComboBox();
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
			onMouseClicked(timeTxt);
		});
		timeTxt.setOnKeyPressed(e->{
			if(e.getCode().toString() == "ENTER"){
				onKeyPressedTextfield();
			}
		});
		return timeTxt;
	}
	
	private Menu createMenuTime(){
		menuTime = new Menu("Insert Time");
		menuTime.getItems().add(createCustomTextField(createTextField()));
		return menuTime;
	}
	

	// -------------------- ACTION METHODS --------------------	
	
	private void onClickComboBox(ComboBox<String> combo){
		message = files[combo.getSelectionModel().getSelectedIndex()];
		client.request(message);
	}
	
	private void onKeyPressedTextfield(){
		menuConfig.hide();
		rd.setTime(timeTxt.getText());
		rd.setConfigsReaderDynamic();
	}
	
	private void onMouseClicked(TextField txt){
		txt.setText("");
	}
	
	private void loadComboBox(){
		files = client.request("begin").split(";");
		this.combo.getItems().addAll(files);
	}
}
