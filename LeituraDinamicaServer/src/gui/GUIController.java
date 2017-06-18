package gui;

import componenets.GUIComponents;
import events.NewEventManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIController {

	GUIComponents components;
	NewEventManager event;
	
	
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
		this.event 		= new NewEventManager();
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
		/*configAlerts();       // Colocar os alerts
		*/
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
	}
	
	private void setMenu(){
		event.setMenuTime(components.getMenuTime());
		event.setMenuFile(components.getMenuFile());
	}
	
	/*
	 * O controller terá uma instância de componentes e de eventos
	 * A única coisa que ele faz é cahamr os métodos da classe evento pra vincular
	 * passando de parâmetro os componentes que sao pedidos, que serão obtidos da
	 * classe GUIComponents !
	 */
	
}