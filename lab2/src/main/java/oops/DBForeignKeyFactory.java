package oops;

import java.sql.ResultSet;

public class DBForeignKeyFactory 
{
    private DBForeignKeyFactory()
    {
    }
    
    public static DBForeignKey DBForeignKeyFactory(ResultSet foreignKeysRS) throws Exception
    {
        String primaryKeyName = foreignKeysRS.getString(8);
        String foreignTableName = foreignKeysRS.getString(3);
        String foreignKeyName = foreignKeysRS.getString(4);
        
        return new DBForeignKey(primaryKeyName, foreignTableName, foreignKeyName);
    }
}
