package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorAlert{
	
	private final String category				= "Erro";
	
	private final String dataError_1 			= "Dado inválido";
	private final String dataMessage_1 			= "Número negativo inserido";
	
	private final String dataError_2			= "Dado inválido";
	private final String dataMessage_2			= "Nenhuma velocidade foi especificada";
	
	private final String dataError_3			= "Velocidade nula";
	private final String dataMessage_3			= "Não é possível realizar a leitura com velocidade igual a zero";
	
	private final String dataError_4			= "Nenhuma velocidade selecionada";
	private final String dataMessage_4			= "Nenhuma velocidade foi selecionada";
	
	private final String dataError_5			= "Nenhum arquivo selecionado";
	private final String dataMessage_5			= "Nenhum arquivo foi selecionado";
	
	private final String dataError_6			= "Nenhuma configuração detectada";
	private final String dataMessage_6			= "Não há configuração para realizar a leitura dinâmica";
	
	private final String connectionError_1		= "Erro de conexão";
	private final String connectionMessage_1 	= "Não foi possível estabelecer a conexão com o servidor";
	
	private final String connectionError_2		= "Erro de requisição";
	private final String connectionMessage_2	= "Não foi possível receber uma resposta do servidor";
	
	private Alert alert;
	
	public void negativeSpeedError() {
		createAlert(dataError_1,dataMessage_1);
		alert.showAndWait();
	}
	
	public void nullSpeedError() {
		createAlert(dataError_2,dataMessage_2);
		this.alert.showAndWait();
	}
	
	public void zeroSpeedError() {
		createAlert(dataError_3,dataMessage_3);
		this.alert.showAndWait();
	}
	
	public void noSpeedSelected() {
		createAlert(dataError_4,dataMessage_4);
		this.alert.showAndWait();
	}
	
	public void noFileSelected() {
		createAlert(dataError_5,dataMessage_5);
		this.alert.showAndWait();
	}
	
	public void showDoubleError() {
		createAlert(dataError_6,dataMessage_6);
		this.alert.showAndWait();
	}
	
	public void connectionError() {
		createAlert(connectionError_1,connectionMessage_1);
		this.alert.showAndWait();
	}
	
	public void requestError() {
		createAlert(connectionError_2,connectionMessage_2);
		this.alert.showAndWait();
	}
	
	private void createAlert(String type,String message) {
		this.alert = new Alert(AlertType.WARNING);
		this.alert.setTitle(category);
		this.alert.setHeaderText(type);
		this.alert.setContentText(message);
	}
}