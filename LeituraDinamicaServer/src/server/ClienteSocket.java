package server;

import java.io.*;
import java.net.*;
public class ClienteSocket{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
	ClienteSocket(){}
	
	public static void main(String args[])
	{
		ClienteSocket client = new ClienteSocket();
		client.enviarDados("word");
	}
	
	public void enviarDados(String dados)
	{
		try{
			requestSocket = new Socket("localhost", 2000);
			System.out.println("Connected to localhost in port 2004");
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			do{
				try{
					sendMessage(dados);
					message = (String)in.readObject();
					System.out.println("client recebendo: " + message);
				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("x-x-x-x FIM x-x-x-x"));
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			//ioException.printStackTrace();
		}
		finally{
			try{
				in.close();
				out.close();
				requestSocket.close();
				System.out.println("Fechei sabudega !");
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
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
  