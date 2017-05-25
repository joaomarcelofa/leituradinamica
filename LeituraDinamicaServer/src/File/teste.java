package File;

public class teste {
	
	public static void main(String args[]){
		
		String path = System.getProperty("user.dir");
		path = path + "\\Texts";
		
		String newPath = path;
		
		char barra = '\'';
		String novo = System.getProperty("file.separator");
		
		newPath.replace(barra,novo.charAt(0));
		
		System.out.println(newPath);
		
	}
	
}
