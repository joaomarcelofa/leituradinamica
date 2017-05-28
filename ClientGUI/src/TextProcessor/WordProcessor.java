package TextProcessor;

import java.util.ArrayList;

public class WordProcessor {
	
	private int lineCursor = 0;
	private int wordCursor = 0;
	
	public void processFile(ArrayList<String> lines){
		for(String actualLine :  lines) {
			manageLine(splitLine(actualLine));
		}
	}
	
	private void manageLine(String splitedLine[]){
		for(String word: splitedLine) {
			System.out.println(splitedLine[this.wordCursor]);
			advanceWord();
		}
		resetWordCursor();
	}
	
	private String[] splitLine(String actualLine){		
		return actualLine.split(" ");
	}
	
	private void advanceWord(){
		this.wordCursor++;
	}
	
	private void resetWordCursor() {
		this.wordCursor = 0;
	}
	
}
