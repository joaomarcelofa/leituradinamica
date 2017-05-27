package File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import sendInformation.WordProcessor;

public class teste {
	
	public static void main(String args[]){
		
		//String path = System.getProperty("user.dir");
		//path = path + "\\Texts";
		
		int path;
		
		JFileChooser chooseFile = new JFileChooser();
		path = chooseFile.showOpenDialog(null);
		
		/*String newPath = path;
		
		char barra = '\'';
		String novo = System.getProperty("file.separator");
		
		newPath.replace(barra,novo.charAt(0));
		*/
		
		if(path == JFileChooser.APPROVE_OPTION) {
			Reader newFile = new Reader();
			newFile.open(chooseFile.getSelectedFile().getAbsolutePath());
			ArrayList <String> lines = new ArrayList <>();
			lines = newFile.read();
			WordProcessor processor = new WordProcessor();
			processor.processFile(lines);
			newFile.close();
		}
	}
	
}
