package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import sqlite.SqLiteErrorLog;

/**
 * Classe ErrorAlert � respons�vel pelos erros que t�m origem em exce��es ou que t�m origem 
 *  com base nos requisitos. Aborda erros de conex�o com BD, Server ou erros de configura��es
 *  da leitura din�mica. Seu construtor instancia uma vari�vel global log da classe SqLiteErrorLog,
 *  e seu m�todos t�m a responsabilidade de criar um alerta, mostr�-lo e inserir o erro no banco de
 *  dados atrav�s do log com base no erro capturado, e por fim, o m�todo createAlert() fica respons�vel
 *  por criar um alerta com o type e message de acordo com o erro.
 */
public class ErrorAlert{
	
	private SqLiteErrorLog log;
	
	public final String DATA_ERROR_DESCRIBE	 	  = "Dado inválido";
	public final String CONNECTION_ERROR_DESCRIBE = "Erro na conexao";
	
	public final String DATA_MESSAGE_1 = "Velocidade negativa inserida";
	public final String DATA_MESSAGE_2 = "Velocidade inserida não é um número inteiro";
	public final String DATA_MESSAGE_3 = "Velocidade nula inserida (zero)";
	public final String DATA_MESSAGE_4 = "Velocidade não especificada";
	public final String DATA_MESSAGE_5 = "Arquivo de texto não selecionado";
	public final String DATA_MESSAGE_6 = "Não houve configuração de texto e velocidade";
	
	public final String CONNECTION_MESSAGE_1 = "Não foi possível se conectar ao servidor";
	public final String CONNECTION_MESSAGE_2 = "Não foi possível receber uma resposta do servidor";
	
	public final int DATACODE 		= -1;
	public final int CONNECTIONCODE = -2;
	
	private Alert alert;
	
	public ErrorAlert() {
		this.log = new SqLiteErrorLog();
	}
	
	public void negativeSpeedError() {
		createAlert(DATA_ERROR_DESCRIBE,DATA_MESSAGE_1);
		alert.showAndWait();
		log.insert(DATACODE, DATA_ERROR_DESCRIBE, DATA_MESSAGE_1);
	}
	
	public void nullSpeedError() {
		createAlert(DATA_ERROR_DESCRIBE,DATA_MESSAGE_2);
		this.alert.showAndWait();
		log.insert(DATACODE, DATA_ERROR_DESCRIBE, DATA_MESSAGE_2);
	}
	
	public void zeroSpeedError() {
		createAlert(DATA_ERROR_DESCRIBE,DATA_MESSAGE_3);
		this.alert.showAndWait();
		log.insert(DATACODE, DATA_ERROR_DESCRIBE, DATA_MESSAGE_3);
	}
	
	public void noSpeedSelected() {
		createAlert(DATA_ERROR_DESCRIBE,DATA_MESSAGE_4);
		this.alert.showAndWait();
		log.insert(DATACODE, DATA_ERROR_DESCRIBE, DATA_MESSAGE_4);
	}
	
	public void noFileSelected() {
		createAlert(DATA_ERROR_DESCRIBE,DATA_MESSAGE_5);
		this.alert.showAndWait();
		log.insert(DATACODE, DATA_ERROR_DESCRIBE, DATA_MESSAGE_5);
	}
	
	public void showDoubleError() {
		createAlert(DATA_ERROR_DESCRIBE,DATA_MESSAGE_6);
		this.alert.showAndWait();
		log.insert(DATACODE, DATA_ERROR_DESCRIBE, DATA_MESSAGE_6);
	}
	
	public void connectionError() {
		createAlert(CONNECTION_ERROR_DESCRIBE,CONNECTION_MESSAGE_1);
		this.alert.showAndWait();
		log.insert(DATACODE, CONNECTION_ERROR_DESCRIBE, CONNECTION_MESSAGE_1);
	}
	
	public void requestError() {
		createAlert(CONNECTION_ERROR_DESCRIBE,CONNECTION_MESSAGE_2);
		this.alert.showAndWait();
		log.insert(DATACODE, CONNECTION_ERROR_DESCRIBE, CONNECTION_MESSAGE_2);
	}
	
	private void createAlert(String type,String message) {
		this.alert = new Alert(AlertType.WARNING);
		this.alert.setTitle("ERRO");
		this.alert.setHeaderText(type);
		this.alert.setContentText(message);
	}
}