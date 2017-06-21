package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A classe Reader que implementa a interface IFile implementa os m�todos respons�veis para a 
 * manipula��o de arquivos, como: open(), read(), write() e close(). Al�m disso, h� o m�todo
 * searchFilesComboBox que tem o papel de pegar todos os arquivos no diret�rio n prjeto e 
 * list�-los no combobox da aplica��o. Os demais m�todos t�m o papel de procurar determinado 
 * arquivo na pasta do projeto, e ler as linhas desse arquivo selecionado.
 *
 */
public class Reader implements IFile {

	private File file = null;
	private FileReader fReader = null;
	private BufferedReader buffer = null;
	
	public void open(String name){
		try{
			this.file = new File(searchFile(name));
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
	
	public String searchFilesComboBox(){
		String path = searchFile("");
		String files = "";
		File[] listfiles = new File(path).listFiles();
		for (File file : listfiles) {
		    if (file.isFile()) {
		        files += file.getName() + ";";
		    }
		}
		return files;
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
	
	// ------------------------ Private Methods ------------------------
	
	private String searchFile(String name){
		String path = System.getProperty("user.dir");
		path += "\\Texts\\";
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
