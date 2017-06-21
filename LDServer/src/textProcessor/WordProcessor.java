package textProcessor;

import java.util.ArrayList;

/**
 * A classe WordProcessor tem o papel de ler as linhas do texto e mandar palavra por palavra
 * para o servidor, quando requisitado. 
 *
 */
public class WordProcessor {
	
	private int lineCursor;
	private int wordCursor;
	private int lineLimit;
	private int wordLimit;
	
	private String[] actualLine;
	private ArrayList<String> lines;
	private String currentWord;
	
	private boolean isFinished;
	
	/**
	 * The server will not receive empty file
	 * The pre-condition is : Someone will upload a valid file 
	*/
	
	public void processFile(){
		manageLine();
	}
	
	public WordProcessor(ArrayList <String> lines){
		this.lines = lines;
		this.actualLine  = splitLine(lines.get(0));
		this.currentWord = "";
		
		this.lineCursor = 0;
		this.wordCursor = 0;
		this.wordLimit  = actualLine.length - 1;
		this.lineLimit  = lines.size() - 1;
		
		this.isFinished = false;
	}
	
	public void reset(){
		this.actualLine  = splitLine(lines.get(0));
		this.currentWord = "";
		this.lineCursor  = 0;
		this.wordCursor  = 0;
		this.wordLimit   = actualLine.length - 1;
		this.lineLimit   = lines.size() - 1;
		this.isFinished  = false;
	}
	
	public String getCurrentWord() {
		return this.currentWord;
	}
	
	public boolean isFinished() {
		return this.isFinished;
	}
	
	// -------------------- PRIVATE METHODS --------------------
	
	
	private void manageLine(){
		
		if(this.wordCursor <= this.wordLimit) {
			setCurrentWord();
			advanceWord();
		}
		else	{
			resetWordCursor();
			
			if(this.lineCursor < this.lineLimit) {
				advanceLineCursor();
				changeLine();
				setWordLimit();
				
				setCurrentWord();
				advanceWord();
			}
			else {
				this.isFinished  = true;
				this.currentWord = "x-x-x-x FIM x-x-x-x";
			}
		}
		
	}
	
	private String[] splitLine(String line){
		return line.split(" ");
	}
	
	private void advanceWord(){
		this.wordCursor++;
	}
	
	private void resetWordCursor() {
		this.wordCursor = 0;
	}
	
	private void advanceLineCursor() {
		this.lineCursor++;
	}
	
	private void changeLine(){
		this.actualLine = splitLine(lines.get(this.lineCursor));
	}
	
	private void setWordLimit() {
		this.wordLimit = actualLine.length - 1;
	}
	
	private void setCurrentWord(){
		this.currentWord = actualLine[this.wordCursor];
	}
}
