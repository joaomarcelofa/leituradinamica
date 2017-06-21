package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import File.Reader;
import textProcessor.WordProcessor;

/**
 * A classe Server tem o papel de conectar o servidor a aplica��o, utilizando o ServerSocket.
 *
 */
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
		try {
		
			this.providerSocket = new ServerSocket(2000,10);
			this.connection = new Socket();
			
			String received;
			System.out.println("Esperando o cliente se conectar !");
			connection = providerSocket.accept();
			System.out.println("Chegou o cliente !");
			
			this.out = new ObjectOutputStream(connection.getOutputStream());
			this.out.flush();
			this.in  = new ObjectInputStream(connection.getInputStream());
			
			
			do {
				try {
					received = (String)in.readObject();
					switch(received) {
						case "word":
							sendWord();
							break;
						
						case "stop":
							sendStop();
							break;
						
						case "begin":
							loadComboBox();
							break;
						
						case "close":
							closeConnection();
							break;
						
						default:
							System.out.println(received);
							getTitleText(received);
					}
				}
				catch(SocketException e){
					System.out.println("server ex");
					this.run();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}while(true);
		}
		catch (IOException e) {
			e.printStackTrace();
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
	
	private void loadComboBox(){
		try {
			this.out.writeObject(this.newFile.searchFilesComboBox());
			this.out.flush();
		}
		catch(Exception ex) {
			ex.getStackTrace();
			System.out.println("ERROU !");
		}
	}
	
	private void closeConnection() throws IOException{
		try{
			this.providerSocket.close();
			this.out.writeObject("closing");
			this.out.flush();
			this.in.close();
			this.out.close();
		}
		catch(Exception ex){
			System.out.println("exception server");
		}
		
	}
	
	private void getTitleText(String nameFile){
		try{
			this.newFile.open(nameFile);
			this.lines = new ArrayList <>();
			this.lines = newFile.read();
			this.processor = new WordProcessor(lines);
			this.out.writeObject("");
			this.out.flush();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
}