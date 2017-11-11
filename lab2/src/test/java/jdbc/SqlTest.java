package jdbc;
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
public class SqlTest     extends TestCase {  
   /**      * Create the test case      *      * @param testName name of the test case      */   
	static Connection sakilaConn = null;
	static PreparedStatement sakilaPrepareStat = null;  
		public SqlTest( String testName )   
   {       
		super( testName );    
   } 

 /**      * @return the suite of tests being tested      */   
	public static Test suite()    
	{       
			System.out.println("COUCOU");      
			return new TestSuite( SqlTest.class );   
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
			// MySQL Select Query 
			String getQueryStatement = "SELECT * FROM actor";
			makeJDBCConnection();
			sakilaPrepareStat = sakilaConn.prepareStatement(getQueryStatement);
 
			// Execute the Query, and get a java ResultSet
			ResultSet rs = sakilaPrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			ResultSetMetaData rs1 = rs.getMetaData();
				int numberOfColumns = rs1.getColumnCount();
				for (int i = 1; i <= numberOfColumns; i++) {
					String label = rs1.getColumnName(i) + " | ";
					System.out.print(label);
				}
				System.out.println();
				while (rs.next()) {
					String row = "";
					for (int i = 1; i <= numberOfColumns; i++) {
						row += rs.getString(i) + " | ";
					}
					System.out.print(row);
					System.out.println();
				}
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
	} 
	} 