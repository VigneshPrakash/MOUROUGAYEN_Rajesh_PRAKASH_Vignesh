package sqlprogram; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.Test; 
import junit.framework.TestCase; 
import junit.framework.TestSuite; 
import java.sql.*;
 
/**  
* Unit test for simple App.  */ 
public class SakilaTest     extends TestCase {  
   /**      * Create the test case      *      * @param testName name of the test case      */   
	static Connection sakilaConn = null;
	static PreparedStatement sakilaPrepareStat = null;  
		public SakilaTest( String testName )   
   {       
		super( testName );    
   } 

 /**      * @return the suite of tests being tested      */   
 public static Test suite()    
 {       
	System.out.println("COUCOU");      
	return new TestSuite( SakilaTest.class );   
 } 
 private static void makeJDBCConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			log("Congrats - Seems your MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return;
		}
 
		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			sakilaConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "root");
			if (sakilaConn != null) {
				log("Connection Successful! Enjoy. Now it's time to push data");
			} else {
				log("Failed to make connection!");
			}
		} catch (SQLException e) {
			log("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		}
 
	}
	private static void log(String string) {
		System.out.println(string);
 
	}
    /**      * Rigourous Test :-)      */   
	public void testApp()   
	{         assertTrue( true );  
			try {
						makeJDBCConnection();
						DatabaseMetaData databaseMetaData = sakilaConn.getMetaData();//connection with the database metadata class
						String   catalog          = null;
						String   schemaPattern    = null;
						String   tableNamePattern = null;
						String[] types            = null;
						ResultSet result = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types );
						String tableName ="";
						while(result.next()) {
								tableName = result.getString(3);
								System.out.println(tableName);
						}
			}catch (SQLException e) {
					e.printStackTrace();
					}
	} 
}