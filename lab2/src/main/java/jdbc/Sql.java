package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.String.*;
import java.util.*;
import java.sql.*;
import org.apache.log4j.*;
import javax.sql.*;

public class Sql {
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static String query;
	
	private Connection sakilaConn;
	private Statement statement;
	private PreparedStatement sakilaPrepareStat;
	private ResultSet resultSet;
	
	public static void main(String[] args) {
		
		try {
			Sql x = new Sql(); //Access variables
			x.getArgs(args); //Get given arguments
			x.openConnection(url,user,password); //Connect to the DB
			x.printResults(); //Show the results
			x.closeConnection(); //Disconnect from the DB
		}   
		catch(Exception e) {
				e.printStackTrace();
		} 
	}
	
	public void getArgs(String[] args) {
		try {
			System.out.println("GIVEN ARGUMENTS :");
			url = args[0];
			driver = args[1];
			user = args[2];
			password = args[3];
			query = args[4];
			//Print given arguments
			System.out.println("URL: " +url + "\nDriver:" +driver + "\nUser: " + user + "\nPassword: " + password + "\nQuery: " + query); 
		}
		catch(Exception e){
			System.out.println("Improper query check the command line you have typed");
		}
	}
	
	
	public void openConnection(String url, String user, String password) throws Exception {
		Class.forName(driver); //Driver registration
		sakilaConn = DriverManager.getConnection(url,user,password); //Connect to the DB
		System.out.println("Connection is now opened");
	}
	
	
	public void printResults() throws Exception {
		//CASE SELECT
		if(query.startsWith("SELECT")) {
				statement = sakilaConn.createStatement();
				resultSet = statement.executeQuery(query);
				
				int rows = resultSet.getMetaData().getColumnCount();
				ResultSetMetaData data = resultSet.getMetaData();
				
				String header = "";
				for (int i=1; i <= rows; i++) {
					header = header + data.getColumnLabel(i) + " \t ";
				}
				
				//Print the header of the result table
				System.out.println("\nID \t " + header + "\n");
				
				//Print the lines of the result table
				int row = 0;
				while(resultSet.next()) {
					String field = "";
					field += row + " \t ";
					for (int i=1; i <= rows; i++) {
						field = field + resultSet.getString(i) + " \t ";
					}
					row++;
					
					System.out.println(field);
				}
				
				resultSet.close();
				statement.close();
			}
			else { //OTHER CASES
				sakilaPrepareStat= sakilaConn.prepareStatement(query);
				int rows = sakilaPrepareStat.executeUpdate();
				System.out.println("Number of rows impacted: " + rows);
				sakilaPrepareStat.close();
			}
	}
	
	public void closeConnection() throws Exception {
		sakilaConn.close();
		System.out.println("Connection is now closed");
	}
}
