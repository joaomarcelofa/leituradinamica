package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import File.Reader;
import textProcessor.WordProcessor;

public class Server {

	private Reader newFile;
	private ArrayList <String> lines;
	private WordProcessor processor;
	
	private ServerSocket providerSocket;
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public static void main(String args[]) {
		
		Server myServer = new Server();		
		myServer.run();	
	}
	
	private void run() {
		
		this.newFile = new Reader();
		this.newFile.open("Texte.txt");

		this.lines = new ArrayList <>();
		this.lines = newFile.read();
		this.processor = new WordProcessor(lines);
		
		try {
		
			this.providerSocket = new ServerSocket(2000,10);
			this.connection = new Socket();
			
			String received;
			
			System.out.println("Esperando o cliente se conectar !");
			connection = providerSocket.accept();
			System.out.println("YAY ! Chegou o cliente !");
			
			this.out = new ObjectOutputStream(connection.getOutputStream());
			this.in  = new ObjectInputStream(connection.getInputStream());
			this.out.flush();
			
			do {
				try {
					received = (String)in.readObject();
					
					switch(received) {
						case "word":{
							sendWord();
							break;
						}
						
						case "stop":{
							sendStop();
							break;
						}
					}
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}while(true);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try{
				this.in.close();
				this.out.close();
				this.providerSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	private void sendWord() {
		this.processor.processFile();
		try {
			this.out.writeObject(this.processor.getCurrentWord());
			this.out.flush();
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void sendStop() {
		this.processor.reset();
		try {
			this.out.writeObject("");
			this.out.flush();
		}
		
		catch(Exception ex) {
			
		}
	}
	
}