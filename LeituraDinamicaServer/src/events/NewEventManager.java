package events;

import alerts.ErrorAlert;
import client.Client;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import readerDynamic.DynamicReader;

public class NewEventManager {

	private Client 			client;
	public  DynamicReader 	rd;
	
	private Menu menuFile;
	private Menu menuTime;
	
	private String[] files;
	
	private Button play;
	private Button pause;
	private Button stop;
	
	private boolean isFileSet;
	private boolean isSpeedSet;
	private boolean isReady = false;	
	
	// --------------- Connection ---------------

	public void createConnectionGUI(){
		try {
			this.client = new Client();
			this.client.createConnection();
		}
		catch(Exception ex) {
			new ErrorAlert().connectionError();
		}
	}
	
	public void closeConnection(){
		System.out.println(this.client.request("close"));
		this.client.closeConnection();
	}
	
	// --------------- Setting DynamicReader ---------------
	
	public void createReaderDynamic(){
		this.rd = new DynamicReader(client);
	}
	
	public void setVisorRD(TextField visor){
		this.rd.setVisor(visor);
	}
	
	// --------------- Menu File and Time ---------------
	
	public void setMenuFile(Menu menuFile){
		this.menuFile = menuFile;
	}
	
	public void setMenuTime(Menu menuTime){
		this.menuTime = menuTime;
	}
	
	
	// --------------- Menu - ComboBox - File ---------------
	
	public void updateComboBox(ComboBox<String> combo) {
		this.files = this.client.request("begin").split(";");
		combo.getItems().addAll(getItens());
	}
	
	private String[] getItens() {
		return this.files;
	}
	
	public void onClickFileCombo(ComboBox<String> combo){
		combo.setOnAction(e->{
			String option = files[combo.getSelectionModel().getSelectedIndex()];
			try {
				this.client.request(option);
				validateFile();
			}
			catch(Exception ex) {
				new ErrorAlert().requestError();
			}
		});
	}
	
	// --------------- Creating Timeline ---------------
	
	public void onClickOkButton(Button bttOk, TextField speedField, Menu menuConfig){
		bttOk.setOnAction(e->{
			setSpeed(speedField);
			createTimeline();
			menuConfig.hide();
		});
	}

	// --------------- Set speed Event ---------------
	
	private void setSpeed(TextField speedField) {
		String speed = speedField.getText();
		
		this.isSpeedSet = false;
		this.isReady 	= false;
		
		try {
			int nSpeed = Integer.parseInt(speed);
			
			if(nSpeed < 0) {
				new ErrorAlert().negativeSpeedError();
			}
			else if(nSpeed == 0) {
					new ErrorAlert().zeroSpeedError();
				}
				else {
					this.rd.setTime(speed);
					validateSpeed();
				}
		}
		catch(NumberFormatException ex) {
			new ErrorAlert().nullSpeedError();
		}
	}
	
	private void createTimeline(){
		this.rd.prepareDynamicReader();
	}
	
	public void onClickField(TextField speedField){
		speedField.setOnMouseClicked(e->{
			resetField(speedField);
		});
	}
	
	private void resetField(TextField speedField) {
		speedField.setText("");
	}
	
	// --------------- Buttons Event ---------------
	
	public void setButtons(Button play, Button pause, Button stop) {
		this.play 	= play;
		this.pause 	= pause;
		this.stop 	= stop;
		manageButton("stop");
	}
	
	public void setPlayEvent(Button play) {
		play.setOnAction(e->{
			verifyConditions();
			if(this.isReady == true) {
				manageButton("play");
				this.rd.play();
				setMenuFileDisable(true);
				setMenuTimeDisable(true);
			}
			else {
				showAlerts();
			}
			
		});
	}
	
	public void setPauseEvent(Button pause) {
		pause.setOnAction(e->{
			manageButton("pause");
			this.rd.pause();
			setMenuFileDisable(true);
			setMenuTimeDisable(false);
		});
	}
	
	public void setStopEvent(Button stop, TextField visor, TextField speedField) {
		stop.setOnAction(e->{
			manageButton("stop");
			visor.setText(this.client.request("stop"));
			this.rd.stop();
			setMenuFileDisable(false);
			setMenuTimeDisable(false);
			speedField.setText("em milisegundos");			
		});
	}
	
	private void setMenuTimeDisable(boolean disable){
		this.menuTime.setDisable(disable);
	}
	
	private void setMenuFileDisable(boolean disable){
		this.menuFile.setDisable(disable);
	}
	
	// --------------- Guards ---------------
	
	private void validateFile() {
		this.isFileSet = true;
	}
	
	private void validateSpeed() {
		this.isSpeedSet = true;
	}
	
	private void verifyConditions() {
		if(this.isFileSet == true && isSpeedSet == true) {
			this.isReady = true;
		}
		else {
			this.isReady = false;
		}
	}
	
	// --------------- Alerts on Play Button ---------------
	
	private void showAlerts() {
		if(this.isFileSet == false && this.isSpeedSet == false){
			new ErrorAlert().showDoubleError();
		}
		else if (this.isFileSet == false){
				new ErrorAlert().noFileSelected();
			}
			else {
				new ErrorAlert().noSpeedSelected();
			}
	}
	
	// --------------- Manage Button ---------------
	
	private void manageButton(String buttonCode) {
		
		switch(buttonCode) {
		
			case "play" :{
					this.play.setDisable(true);
					this.pause.setDisable(false);
					this.stop.setDisable(false);
				break;
			}
			
			case "pause":{
				this.play.setDisable(false);
				this.pause.setDisable(true);
				this.stop.setDisable(false);
			break;
		}
				
			case "stop" :{
					this.play.setDisable(false);
					this.pause.setDisable(true);
					this.stop.setDisable(true);
				break;
			}
		}
	}
	
}