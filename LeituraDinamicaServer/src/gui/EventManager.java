package gui;

import client.Client;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import readerDynamic.DynamicReader;

public class EventManager {
/*	
	private Client 			client;
	public  DynamicReader 	rd;
	private Panel 			painel;
	private MenuManager 	menu;
	
	private String	 message = "";
	private String[] files;
	
	// --------------- Setters of Panel and MenuManager ---------------
	
	public void setPanel(Panel painel){
		this.painel = painel;
	}
	
	public void setMenu(MenuManager menu){
		this.menu = menu;
	}
	
	
	// --------------- Connection Methods ---------------
	
	public void createConnectionGUI(){
		this.client = new Client();
		this.client.createConnection();
		this.files = this.client.request("begin").split(";");
	}
	
	public void closeView(){
		System.out.println(this.client.request("close"));
		this.client.closeConnection();
	}
	
	
	// --------------- DynamicReader Methods ---------------
	
	public void createReaderDynamic(){
		this.rd = new DynamicReader(client);
	}
	
	
	// --------------- MenuManager Methods ---------------
	
	public void onClickComboBox(ComboBox<String> combo){
		message = files[combo.getSelectionModel().getSelectedIndex()];
		this.client.request(message);
		this.setMenuTimeDisable(false);
	}
	
	public String[] loadComboBox(){
		return this.files;
	}
	
	public void onMouseClicked(TextField txt){
		txt.setText("");
	}
	
	public void onKeyPressedTextfield(Menu menuConfig, TextField timeTxt){
		menuConfig.hide();
		this.rd.setTime(timeTxt.getText());
		this.rd.prepareDynamicReader();
		ButtonsEnable();
	}
	
	private void ButtonsEnable(){
		this.painel.getButton("Play").setDisable(false);
		this.painel.getButton("Pause").setDisable(false);
		this.painel.getButton("Stop").setDisable(false);
	}
	
	
	// --------------- Panel Methods ---------------
	
	public void setVisor(TextField visor){
		this.rd.setVisor(visor);
	}
	
	public void onPlay(Button bttPlay){
		this.rd.play();
		bttPlay.setText("PLAY");
		this.setMenuDisable("Play");
	}
	
	public void onPause(Button bttPlay){
		this.rd.pause();
		//TODO : Remove or not ?
		if (bttPlay.getText().equals("PLAY")){
			bttPlay.setText("REINICIAR");
			this.setMenuDisable("Pause");
		}
	}
	
	public void onStop(TextField visor, Button bttPlay){
		visor.setText(this.client.request("stop"));
		this.rd.stop();
		bttPlay.setText("PLAY");
		this.setMenuDisable("Stop");
	}
	
	private void setMenuDisable(String status){
		switch (status){
			case "Play" :
				this.setMenuFileDisable(true);
				this.setMenuTimeDisable(true);
				break;
			case "Pause" :
				this.setMenuFileDisable(true);
				this.setMenuTimeDisable(false);
				break;
			case "Stop" :
				this.setMenuFileDisable(false);
				this.setMenuTimeDisable(false);
				break;
		}
	}
	
	private void setMenuFileDisable(boolean bol){
		this.menu.getMenuFile().setDisable(bol);
	}
	
	private void setMenuTimeDisable(boolean bol){
		this.menu.getMenuTime().setDisable(bol);
	}
	*/
}