package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader implements IFile {

	private File file = null;
	private FileReader fReader = null;
	private BufferedReader buffer = null;
	
	
	public void open(String name){
		//String path = searchFile();
		try{
			this.file = new File(name);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<String> read(){

		ArrayList<String>lines = new ArrayList<String>();
		
    	try{
    		this.fReader = new FileReader(this.file);
        	this.buffer = new BufferedReader(this.fReader);
        	System.out.println("Consegui Abrir !");
        	
        	lines = readLines();
        	
    	}
    	catch(FileNotFoundException e){
    		System.out.println("Nao abriu a bagaça :(");
			e.printStackTrace();
    	}
    	finally {
    		return lines;
    	}
	}
	
	public void write(String text){
		//Method not used
	}

	public void close(){
		try{
			this.buffer.close();
			this.fReader.close();
		}
		catch(Exception ex){
			System.out.println("Não conseguiu fechar");
		}
	}
	
	// Private Methods
	
	private String searchFile(String name){
		String path = System.getProperty("user.dir");
		path += "\\Texts";
		path += name;
		path = formatPath(path);
		return path;
	}
	
	private String formatPath(String filePath){
		String newPath = filePath;
		char barra = '\'';
		String novo = System.getProperty("file.separator");
		newPath.replace(barra,novo.charAt(0));
		return newPath;
	}
	
	private ArrayList<String> readLines(){
		ArrayList<String> lines = new ArrayList<>();
		String line;
		
		try {
			line = this.buffer.readLine();
			
			while(line != null) {
				lines.add(line);
				line = this.buffer.readLine();
			}
		
		}
		catch (IOException e) {
			System.out.println("Não consgui ler a linha");
			e.printStackTrace();
		}
		
		
		return lines;
	}
}
