package events;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import readerDynamic.DynamicReader;

public class NewEventManager {

	private Client 			client;
	public  DynamicReader 	rd;
	
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
	
	// --------------- Menu - ComboBox - File ---------------
	
	public void updateComboBox(ComboBox<String> combo) {
		this.files = this.client.request("begin").split(";");
		combo.getItems().addAll(getItens());
	}
	
	private String[] getItens() {
		return this.files;
	}
	
	// --------------- Set speed Event ---------------
	
	public void onClickOkButton(Button bttOk, TextField speedField){
		bttOk.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				setSpeed(speedField);
				
			}
		});
	}
	
	private void setSpeed(TextField speedField) {
		this.rd.setTime(speedField.getText());
	}
	
	// --------------- Mount Timeline ---------------
	
	public void mountTimeline() {
		
	}
	
	// --------------- Buttons Event ---------------
	
	public void setButtonEvents(Button play, Button pause, Button stop) {
		setPlayEvent(play);
		setPauseEvent(pause);
		setStopEvent(stop);
	}
	
	private void setPlayEvent(Button play) {
		
	}
	
	private void setPauseEvent(Button pause) {
		
	}
	
	private void setStopEvent(Button stop) {
		
	}
	
	
	//TODO : Temos que implementar os seguintes eventos:
	/*
	 * Request do ComboBox para preencher ele com o nome das files
	 * 
	 * Setar a velocidade do Negocio para configurar a Timeline
	 * 
	 * Vincular o Play,Pause e Stop com os bot�es (Podemos fazer isso depois de se
	 * escolher a file, pq desabilitar os bot�es passa a sensa��o que est� algo 
	 * errado, veja se concorda cmg )
	 * 
	 * Colocar os Alerts que eu fiz, mas � depois de ter implementado tudo
	 * 
	 * Possivelmente colocar o banco de Dados tbm, verei isso amanh� 12/06
	 * 
	 * Acho que � s� ! Vamos tentar separar os neg�cios
	 * */
	
}