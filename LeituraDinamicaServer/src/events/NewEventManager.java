package events;

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
	
	
	// --------------- Connection ---------------

	public void createConnectionGUI(){
		this.client = new Client();
		this.client.createConnection();
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
			String message = files[combo.getSelectionModel().getSelectedIndex()];
			this.client.request(message);			
		});
	}
	
	
	// --------------- Set speed Event ---------------
	
	public void onClickOkButton(Button bttOk, TextField speedField, Menu menuConfig){
		bttOk.setOnAction(e->{
			setSpeed(speedField);
			createTimeline();
			menuConfig.hide();
		});
	}

	private void setSpeed(TextField speedField) {
		this.rd.setTime(speedField.getText());
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
	
	// --------------- Mount Timeline ---------------
	
	public void mountTimeline() {
		
	}
	
	// --------------- Buttons Event ---------------
	
	/*public void setButtonEvents(Button play, Button pause, Button stop) {
		setPlayEvent(play);
		setPauseEvent(pause);
		setStopEvent(stop);
	}*/
	
	public void setPlayEvent(Button play) {
		play.setOnAction(e->{
			this.rd.play();
			setMenuFileDisable(true);
			setMenuTimeDisable(true);
		});
	}
	
	public void setPauseEvent(Button pause) {
		pause.setOnAction(e->{
			this.rd.pause();
			setMenuFileDisable(true);
			setMenuTimeDisable(false);
		});
	}
	
	public void setStopEvent(Button stop, TextField visor, TextField speedField) {
		stop.setOnAction(e->{
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
	
	
	//TODO : Temos que implementar os seguintes eventos:
	/*
	 * Request do ComboBox para preencher ele com o nome das files - ok
	 * 
	 * Setar a velocidade do Negocio para configurar a Timeline - ok
	 * 
	 * Vincular o Play,Pause e Stop com os botões (Podemos fazer isso depois de se
	 * escolher a file, pq desabilitar os botões passa a sensação que está algo 
	 * errado, veja se concorda cmg )
	 * 
	 * Colocar os Alerts que eu fiz, mas é depois de ter implementado tudo
	 * 
	 * Possivelmente colocar o banco de Dados tbm, verei isso amanhã 12/06
	 * 
	 * Acho que é só ! Vamos tentar separar os negócios
	 * */
	
}