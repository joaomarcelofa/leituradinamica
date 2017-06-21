package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import sqlite.SqLiteErrorLog;

/**
 * Classe ErrorAlert é responsável pelos erros que têm origem em exceções ou que têm origem 
 *  com base nos requisitos. Aborda erros de conexão com BD, Server ou erros de configurações
 *  da leitura dinâmica. Seu construtor instancia uma variável global log da classe SqLiteErrorLog,
 *  e seu métodos têm a responsabilidade de criar um alerta, mostrá-lo e inserir o erro no banco de
 *  dados através do log com base no erro capturado, e por fim, o método createAlert() fica responsável
 *  por criar um alerta com o type e message de acordo com o erro.
 */
public class ErrorAlert{
	
	private SqLiteErrorLog log;
	
	public final String DATA_ERROR_DESCRIBE	 	  = "Dado invÃ¡lido";
	public final String CONNECTION_ERROR_DESCRIBE = "Erro na conexao";
	
	public final String DATA_MESSAGE_1 = "Velocidade negativa inserida";
	public final String DATA_MESSAGE_2 = "Velocidade inserida nÃ£o Ã© um nÃºmero inteiro";
	public final String DATA_MESSAGE_3 = "Velocidade nula inserida (zero)";
	public final String DATA_MESSAGE_4 = "Velocidade nÃ£o especificada";
	public final String DATA_MESSAGE_5 = "Arquivo de texto nÃ£o selecionado";
	public final String DATA_MESSAGE_6 = "NÃ£o houve configuraÃ§Ã£o de texto e velocidade";
	
	public final String CONNECTION_MESSAGE_1 = "NÃ£o foi possÃ­vel se conectar ao servidor";
	public final String CONNECTION_MESSAGE_2 = "NÃ£o foi possÃ­vel receber uma resposta do servidor";
	
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