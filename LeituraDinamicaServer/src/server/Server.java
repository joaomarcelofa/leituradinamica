package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import File.Reader;
import textProcessor.WordProcessor;
import threads.Temporizador;

public class Server {

	private Reader newFile;
	private ArrayList <String> lines;
	private WordProcessor processor;
	
	private ServerSocket providerSocket;
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private Temporizador time;
	
	public static void main(String args[]) {
		
		Server myServer = new Server();		
		myServer.run();	
	}
	
	public void run() {
		
		this.newFile = new Reader();
		this.newFile.open("Texte.txt");
		
		this.time = new Temporizador();
		this.time.setTimer(1000);
		
		this.lines = new ArrayList <>();
		this.lines = newFile.read();
		this.processor = new WordProcessor(lines);
		
		try {
		
			this.providerSocket = new ServerSocket(2000,10);
			this.connection = new Socket();
			
			boolean isReading = false;
			String received;
			String send;
			
			System.out.println("Esperando o cliente se conectar !");
			connection = providerSocket.accept();
			System.out.println("YAY ! Chegou o cliente !");
			
			this.out = new ObjectOutputStream(connection.getOutputStream());
			this.in  = new ObjectInputStream(connection.getInputStream());
			this.out.flush();
			
			do {
				
				try {
					
					received = (String)in.readObject();
					
					if(received.equals("word")) {
						sendWord();
						isReading = processor.getIsRunning();
					}
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}while(isReading);
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
	
	public void sendWord() {
		
		try {
			this.processor.processFile();
			this.out.writeObject(this.processor.getCurrentWord());
			this.out.flush();
			
		} catch(Exception e) {
			
		}
	}
	
}