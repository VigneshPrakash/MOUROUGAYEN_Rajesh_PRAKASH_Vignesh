package oops;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

public class DBBaseFactory
{
    private static ResultSet tableRS;
    private static DatabaseMetaData data;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static Connection con;
    private static String catalog;//DB name
    private static String schema;//schema of the table
    private static String table;// tables name
    private static String column;//columns name
    private static String[] type;//table or view
	final static Logger logger= Logger.getLogger(DBBaseFactory.class);
	
    private DBBaseFactory() 
    {        
    }
    
    public static DBBase DBBaseFactory(final String dbName) throws Exception         
    {
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/sakila";
		user = "root";
		password = "root"; 
		String[] type = {"TABLE"};
        try
        {
		con = DriverManager.getConnection(url,user,password);
		logger.info("Connection Established");
		System.out.println("\nCONNECTING TO THE DATABASE\n");
        }
        catch(Exception e)
        {
    		logger.error("Error While coonecting the database.  Message."+e.getMessage());
        }
		//To get Data from database
        data = con.getMetaData();
        //To get every table/view from out DB
        tableRS = data.getTables(catalog, schema, table, type);
 
        return new DBBase(dbName, tableRS, data);
    }

    public static ResultSet getTableRS() {
        return tableRS;
    }

    public static DatabaseMetaData getData() {
        return data;
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static Connection getCon() {
        return con;
    }

    public static String getCatalog() {
        return catalog;
    }

    public static String getSchema() {
        return schema;
    }

    public static String getTable() {
        return table;
    }

    public static String getColumn() {
        return column;
    }

    public static String[] getType() {
        return type;
    }
    
    
}
