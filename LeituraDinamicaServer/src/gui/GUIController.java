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
	
	// --------------- Init Event ---------------
	
	private void init() {
		this.components = new GUIComponents();
		components.mountGraphicInterface();
		this.event 		= new NewEventManager();
		event.createConnectionGUI();
	}
	
	private void bindEvents() {
		loadFiles();
		/*
		setTimer(); // Textfield
		makeTimeline(); // Fazer a Timeline 
		configButtons(); // Setar os eventos dos botões
		configAlerts(); // Colocar os alerts
		*/
	}
	
	private void loadFiles() {
		event.updateComboBox(components.getCombo());
	}
	
	
	/*
	 * O controller terá uma instância de componentes e de eventos
	 * A única coisa que ele faz é cahamr os métodos da classe evento pra vincular
	 * passando de parâmetro os componentes que sao pedidos, que serão obtidos da
	 * classe GUIComponents !
	 */
	
}
