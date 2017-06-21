package gui;

import events.EventManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A classe GUIController é responsável por iniciar o GUIComponents e EventManager, ou seja,
 * iniciar a parte de interface grafica e seus eventos, além de fechar a aplicação. O método
 * bindEvents() têm o papel de ligar os eventos aos componentes da GUI. 
 *
 */
public class GUIController {

	GUIComponents components;
	EventManager event;
	
	
	public void launchGUI(Stage janela) {
		init();
		
		Scene cena = new Scene(components.getGUI(), 350, 130);
		janela.setScene(cena);
		janela.setTitle("Leitura Dinamica");
		
		bindEvents();
		
		janela.show();
	}
	
	public void closeGUI(){
		this.event.closeConnection();
	}
	
	// --------------- Init Event ---------------
	
	private void init() {
		this.components = new GUIComponents();
		components.mountGraphicInterface();
		this.event 		= new EventManager();
		event.createConnectionGUI();
	}
	
	private void bindEvents() {
		loadFiles();            // load the file into the combo box
		setFile();	            // event on combo box
		setTimer();             // event on text field - submit the value
		resetTimer();           // event on text field - clear the field
		makeReaderDynamic();    // create the reader dynamic object 
		setVisor();             // set visor to reader dynamic object
		configButtons();        // set the events on the buttons
		setMenu();              //set the menus to the event
	}
	
	private void loadFiles() {
		event.updateComboBox(components.getCombo());
	}
	
	private void setFile(){
		event.onClickFileCombo(components.getCombo());
	}
	private void setTimer(){
		event.onClickOkButton(components.getBttOk(), components.getSpeed(), components.getMenuConfig());
	}
	
	private void resetTimer(){
		event.onClickField(components.getSpeed());
	}
	
	private void makeReaderDynamic(){
		event.createReaderDynamic();
	}
	
	private void setVisor(){
		event.setVisorRD(components.getVisor());
	}
	
	private void configButtons(){
		event.setPlayEvent(components.getBttPlay());
		event.setPauseEvent(components.getBttPause());
		event.setStopEvent(components.getBttStop(), components.getVisor(), components.getSpeed());
		event.setButtons(components.getBttPlay(), components.getBttPause(), components.getBttStop());
	}
	
	private void setMenu(){
		event.setMenuTime(components.getMenuTime());
		event.setMenuFile(components.getMenuFile());
	}
}