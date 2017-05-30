package client;

import java.io.*;
import java.net.*;
public class ClientSocket{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
	
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
	
	public void enviarDados(String dados){
		
		do{
			try{
				sendMessage(dados);
				message = (String)in.readObject();
				System.out.println("client recebendo: " + message);
				
			} catch(ClassNotFoundException classNot){
				System.err.println("data received in unknown format");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}while(!message.equals("x-x-x-x FIM x-x-x-x"));
		
		try{
			in.close();
			out.close();
			requestSocket.close();
			System.out.println("Fechei sabudega !");
			
		} catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}
  