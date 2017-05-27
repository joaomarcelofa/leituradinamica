package textProcessor;

import java.util.ArrayList;

public class WordProcessor {
	
	private int lineCursor;
	private int wordCursor;
	private int lineLimit;
	private int wordLimit;
	
	private boolean isRunning;
	
	private String[] actualLine;
	private ArrayList<String> lines;
	private String currentWord;
	
	public void processFile(){
		manageLine();
	}
	
	public WordProcessor(ArrayList <String> lines){
		this.lines = lines;
		this.actualLine = splitLine(lines.get(0));
		this.currentWord = "";
		
		this.lineCursor = 0;
		this.wordCursor = 0;
		this.wordLimit = actualLine.length - 1;
		this.lineLimit = lines.size() - 1;
		
		this.isRunning = true;
	}

	
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
				this.currentWord = "x-x-x-x FIM x-x-x-x";
				isRunning = false;
			}
		}
		
	}
	
	public boolean getIsRunning() {
		return this.isRunning;
	}
	
	public String getCurrentWord() {
		return this.currentWord;
	}
	
	// -------------------- PRIVATE METHODS --------------------
	
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
