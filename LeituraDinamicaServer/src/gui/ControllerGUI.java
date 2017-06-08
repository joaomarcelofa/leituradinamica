package gui;

import client.Client;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import readerDynamic.ReaderDynamic;

public class ControllerGUI {
	
	private Client client;
	public ReaderDynamic rd;
	
	private String message = "";
	private String[] files;
	
	
	
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
	
	
	// --------------- ReaderDynamic Methods ---------------
	
	public void createReaderDdynamic(){
		this.rd = new ReaderDynamic(client);
	}
	
	
	// --------------- MenuConfig Methods ---------------
	
	public void onClickComboBox(ComboBox<String> combo){
		message = files[combo.getSelectionModel().getSelectedIndex()];
		this.client.request(message);
	}
	
	public String[] loadComboBox(){
		return this.files;
	}
	

	public void onKeyPressedTextfield(Menu menuConfig, String timeTxt){
		menuConfig.hide();
		if (this.rd == null) System.out.println("é nhull");
		this.rd.setTime(timeTxt);
		this.rd.setConfigsReaderDynamic();
	}
	
	public void onMouseClicked(TextField txt){
		txt.setText("");
	}
	
	
	// --------------- Panel Methods ---------------
	
	public void setVisor(TextField visor){
		this.rd.setVisor(visor);
	}
	
	public void onPlay(Button bttPlay){
		this.rd.play();
		bttPlay.setText("PLAY");
	}
	
	public void onPause(Button bttPlay){
		this.rd.pause();
		if (bttPlay.getText().equals("PLAY")){
			bttPlay.setText("REINICIAR");
		}
	}
	
	public void onStop(TextField visor, Button bttPlay){
		visor.setText(this.client.request("stop"));
		this.rd.stop();
		bttPlay.setText("PLAY");
	}
}
