package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import alerts.ErrorAlert;

/**
 * Classe Client é responsável pela conexão com a classe Server. Possui os métodos de 
 * createConnection() e closeConnection(), que cria e fecha, respectivamente a conexão
 * com o Server. Além disso, há o método request(), no qual retorna a mensagem recebida 
 * que foi enviada pelo Server. *
 */
public class Client{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	
	public void createConnection(){
		try {
			requestSocket = new Socket("localhost", 2000);
			System.out.println("Connected to localhost in port 2004");
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
		
		} catch (IOException e) {
			e.printStackTrace();
			new ErrorAlert().connectionError();
		}		
	}
	
	public String request(String code){
		String message = "";
		
		try {
			this.out.writeObject(code);
			this.out.flush();
			message = (String)this.in.readObject();
		}
		catch (IOException e) {
			System.out.println("ioexception");
		} catch (ClassNotFoundException e) {
			message = "ERRO , NAO CONSEGUI CONECTAR";
			System.out.println("classnotfoundexception");
		}
		return message;
	}
	
	public void closeConnection() {
		try {
			in.close();
			out.flush();
			out.close();
			requestSocket.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
}
  