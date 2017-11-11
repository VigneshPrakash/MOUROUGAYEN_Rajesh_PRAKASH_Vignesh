package dblab.hello; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 * Simple JDBC connection
 * 
 */
 
public class App {
 
	static Connection sakilaConn = null;
	static PreparedStatement sakilaPrepareStat = null;
 
	public static void main(String[] argv) {
 
		try {
			makeJDBCConnection();
			log("\n---------- Let's get Data from DB ----------");
			getDataFromDB();
 
			sakilaPrepareStat.close();
			sakilaConn.close(); // connection close
 
		} catch (SQLException e) {
 
			e.printStackTrace();
		}
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
 
	private static void getDataFromDB() {
 
		try {
			// MySQL Select Query 
			String getQueryStatement = "SELECT last_name FROM actor";
 
			sakilaPrepareStat = sakilaConn.prepareStatement(getQueryStatement);
 
			// Execute the Query, and get a java ResultSet
			ResultSet rs = sakilaPrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				String last_name = rs.getString("last_name");
				
 
				// Simply Print the results
				System.out.format("%s \n", last_name);
			}
 
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
 
	}
 
	// Simple log utility
	private static void log(String string) {
		System.out.println(string);
 
	}
}