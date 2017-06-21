package File;

import java.util.ArrayList;

/**
 * A interface IFile possui as assinaturas das funções para manipulação básica de arquivos.
 */
public interface IFile{
	
	public void open(String name);
	
	public ArrayList<String> read();
	
	public void write(String text);
	
	public void close();
}
