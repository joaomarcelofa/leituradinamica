package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader implements IFile {

	private File file = null;
	private FileReader fReader = null;
	private BufferedReader buffer = null;
	
	
	public void open(String name){
		String path = searchFile(name);
		
		try{
			this.file = new File(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void read(){

    	try{
    		this.fReader = new FileReader(this.file);
        	this.buffer = new BufferedReader(this.fReader);
    	}
    	catch(FileNotFoundException e){
    		System.out.println("Nao abriu a bagaça :(");
			e.printStackTrace();
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
	
	
	private String searchFile(String name){
		String path = System.getProperty("user.dir");
		path += "\\Texts";
		path = formatPath(path);
		
		System.out.println(path);
		return path;
	}
	
	private String formatPath(String filePath){
		String newPath = filePath;
		
		char barra = '\'';
		String novo = System.getProperty("file.separator");
		
		newPath.replace(barra,novo.charAt(0));
		return newPath;
	}
	
}
