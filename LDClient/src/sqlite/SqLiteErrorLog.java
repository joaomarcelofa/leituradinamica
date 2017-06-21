package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A classe SqLiteErrorLog é responsável por criar um BD e abrir e fechar a conexão com o BD. 
 * Além disso, possui métodos de inserir, mostrar e limpar os registros. O BD só grava em seu BD
 * os erros que podem ocorrer durante a execução da aplicação.
 */
public class SqLiteErrorLog {

	private Connection connection = null;
	private String url = "jdbc:sqlite:log.db";
	
	public SqLiteErrorLog() {
		try {
			openConnection();
			createTable();
		}
		catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     System.exit(0);
		}
	}
	
	public void insert(int logCode, String logDescribe, String Error) {
		Statement stmt = null;
	    try {
	      connection.setAutoCommit(false);

	      stmt = connection.createStatement();
	      String command =  "INSERT INTO LogTable"+
	    		  			"(LogCode,LogDescribe,Error)" +
	    		  			"VALUES ('" +
	    		  				logCode + "','" +
	    		  				logDescribe + "','" +
	    		  				Error + "')";    		  			
	      stmt.executeUpdate(command);
	      stmt.close();
	      connection.commit();
	      
	    }
	    catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() + "XABU AQUI" );
	      System.exit(0);
	    }
	}
	
	public void showRegisters() {
		Statement stmt = null;
	    try {
	      stmt = connection.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM LogTable;" );
	      
	      while ( rs.next() ) {
	         int id = rs.getInt("ID");
	         int logCode = rs.getInt("LogCode");
	         String logDescribe  = rs.getString("LogDescribe");
	         String error = rs.getString("Error");

	         System.out.println( "ID = " + id );
	         System.out.println( "LogCode = " + logCode);
	         System.out.println( "LogDescribe = " + logDescribe);
	         System.out.println( "Error = " + error);
	         System.out.println();
	      }
	      rs.close();
	      stmt.close();	      
	    } 
	    catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      createTable();
	    }
	}
	
	public void cleanRegisters() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String command = "DROP TABLE IF EXISTS LogTable;";
			
			stmt.executeUpdate(command);
			stmt.close();
			connection.setAutoCommit(true);
		}
		catch(Exception e) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() + "DEU XABU AQUI" );
		     System.exit(0);
		}
		
	}
	
	private void openConnection() throws SQLException, ClassNotFoundException{
		connection = DriverManager.getConnection(url);
		connection.setAutoCommit(false);
		Class.forName("org.sqlite.JDBC");
	}
	
	private void closeConnection() throws SQLException {
		connection.close();
	}
	
	private void createTable() {
		Statement stmt = null;
		try {
			connection = DriverManager.getConnection(url);
			
			stmt = connection.createStatement();
		 	String command = "CREATE TABLE IF NOT EXISTS LogTable" +
					  		 "(ID INTEGER 	 PRIMARY KEY	AUTOINCREMENT," +
					  		 " LogCode		 INT			NOT NULL," +
					  		 " LogDescribe	 TEXT			NOT NULL," +
					  		 " Error		 TEXT			NOT NULL " +
					  		 " )";
		 	stmt.executeUpdate(command);
			stmt.close();
		  }
		  catch(Exception e) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		  }
	}
	
	@Override
	public void finalize() {
		try {
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Fechei a conexÃ£o");
	}
}
