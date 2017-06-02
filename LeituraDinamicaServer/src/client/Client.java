package client;

import java.io.*;
import java.net.*;
public class Client{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	boolean isPlaying;
	
	public void createConnection()
	{
		try {
			requestSocket = new Socket("localhost", 2000);
			System.out.println("Connected to localhost in port 2004");
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			message = "ERRO , NÃO CONSEGUI CONECTAR";
		}
		
		return message;
	}
	
	public void closeConnection() {
		try {
			in.close();
			out.close();
			requestSocket.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
}
  