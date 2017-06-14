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
		loadFiles();
		setTimer(); // Textfield
		/*
		makeTimeline(); // Fazer a Timeline 
		configButtons(); // Setar os eventos dos bot�es
		configAlerts(); // Colocar os alerts
		*/
	}
	
	private void loadFiles() {
		event.updateComboBox(components.getCombo());
	}
	
	private void setTimer(){
		//Pegar o valor do textfield quando clica no bot�o
		
	}
	
	
	/*
	 * O controller ter� uma inst�ncia de componentes e de eventos
	 * A �nica coisa que ele faz � cahamr os m�todos da classe evento pra vincular
	 * passando de par�metro os componentes que sao pedidos, que ser�o obtidos da
	 * classe GUIComponents !
	 */
	
}