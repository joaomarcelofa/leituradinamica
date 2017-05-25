package File;

import java.io.File;

public interface IFile{
	
	public void open(String name);
	
	public void read();
	
	public void write(String text);
	
	public void close();
}
