package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorAlert{
	
	private final String category				= "Erro";
	
	private final String dataError_1 			= "Dado inválido";
	private final String dataMessage_1 			= "Número negativo inserido";
	private final String dataError_2			= "Dado inexistente";
	private final String dataMessage_2			= "Nenhuma velocidade foi especificada";
	private final String connectionError_1		= "Erro de conexão";
	private final String connectionMessage_1 	= "Não foi possível estabelecer a conexão com o servidor";
	
	private Alert alert;
	
	public void negativeSpeedError() {
		createAlert(dataError_1,dataMessage_1);
		alert.showAndWait();
	}
	
	public void nullSpeedError() {
		createAlert(dataError_2,dataMessage_2);
		this.alert.showAndWait();
	}
	
	public void connectionError() {
		createAlert(connectionError_1,connectionMessage_1);
		this.alert.showAndWait();
	}
	
	private void createAlert(String type,String message) {
		this.alert = new Alert(AlertType.WARNING);
		this.alert.setTitle(category);
		this.alert.setHeaderText(type);
		this.alert.setContentText(message);
	}
}