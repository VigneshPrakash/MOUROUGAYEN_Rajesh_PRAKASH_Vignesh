package oops;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBColumnFactory 
{
    private DBColumnFactory() 
    {
    }

    public static DBColumn DBColumnFactory(ResultSet columnRS) throws SQLException 
    {
        String colName = columnRS.getString(4);
        String colType = columnRS.getString("TYPE_NAME");
        int colSize = columnRS.getInt("COLUMN_SIZE");
        int scale = columnRS.getInt("DECIMAL_DIGITS");
        String isNullableBool = columnRS.getString("IS_NULLABLE");
        String isNullable = (isNullableBool.equalsIgnoreCase("YES")) ? "NULL" : "NOT NULL";
        String isAutoIncrementBool = columnRS.getString("IS_AUTOINCREMENT");
        String isAutoIncrement = (isAutoIncrementBool.equalsIgnoreCase("YES")) ? " AUTO_INCREMENT" : "";
        
        return DBColumnFactory(colName, colType, isNullable, colSize, scale, isAutoIncrement);
    }

    public static DBColumn DBColumnFactory(final String colName, final String colType, String isNullable, int colSize, int scale, final String isAutoIncrement)  
    {
        if(colType.equalsIgnoreCase("DECIMAL"))
            return new DBColumnDoubleParam(colName, colType, isNullable, colSize, scale, isAutoIncrement);
        else if(colType.equalsIgnoreCase("BLOB") || colType.equalsIgnoreCase("CLOB") || colType.equalsIgnoreCase("DATE") || colType.equalsIgnoreCase("TIME"))
            return new DBColumnNoParam(colName, colType, isNullable, colSize, isAutoIncrement);
        else
            return new DBColumnSingleParam(colName, colType, isNullable, colSize, isAutoIncrement);
    }
    
    
}
