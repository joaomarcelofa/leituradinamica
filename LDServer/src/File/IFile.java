package File;

import java.util.ArrayList;

/**
 * A interface IFile possui as assinaturas das fun��es para manipula��o b�sica de arquivos.
 */
public interface IFile{
	
	public void open(String name);
	
	public ArrayList<String> read();
	
	public void write(String text);
	
	public void close();
}
