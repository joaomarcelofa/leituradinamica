package File;
import java.util.ArrayList;

import textProcessor.WordProcessor;

public class teste {
	
	public static void main(String args[]){				
		
		Reader newFile = new Reader();
		newFile.open("Texte.txt");
		ArrayList <String> lines = new ArrayList <>();
		lines = newFile.read();
		WordProcessor processor = new WordProcessor(lines);
		
		while(processor.getIsRunning()){
			processor.processFile();
			System.out.println(processor.getCurrentWord());
		}
		
		newFile.close();
		
	}
	
}
