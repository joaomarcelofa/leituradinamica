package Clients;

import java.io.*;
import java.net.*;

public class ClientSocket{
	
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
	
 	void ClienteSocket(){}
	
 	String enviarDados(String dados){
 		
		try{
			//1. creating a socket to connect to the server
			requestSocket = new Socket("172.17.6.9", 2004);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
			do{
				try{
					sendMessage(dados);
					message = (String)in.readObject();
					System.out.println("client recebendo: " + message);
				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}
			}while(message.indexOf(".OK") < 0);
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			//ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
			return message.substring(0, message.indexOf(".OK"));
	}
 	
	void sendMessage(String msg){
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client enviando: " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
}
  
