package oops;

import org.apache.log4j.Logger;

public class DBMain 
{
	final static Logger logger = Logger.getLogger(DBMain.class);
    private static DBBase database;
    private static DBFile DMLFile;
    
    public DBMain()
    {
    }
    
    public static void main(String[] args)
    {
        try
        {
        	logger.info("Entering DBMain method");
            database = DBBaseFactory.DBBaseFactory("sakila");
            String instruction = database.toSQL();
            
            System.out.println(instruction);
            
            DMLFile = DBFileFactory.DBFileFactory(instruction);
     
        }
        catch(Exception e)
        {
        	logger.error("Error While coonecting the database.  Message."+e.getMessage());
            e.printStackTrace();
        }  
    }
}
