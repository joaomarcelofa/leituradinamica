package File;

import java.util.ArrayList;

public interface IFile{
	
	public void open(String name);
	
	public ArrayList<String> read();
	
	public void write(String text);
	
	public void close();
}
