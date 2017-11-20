package oops;

import java.sql.ResultSet;

public class DBPrimaryKeyFactory 
{
    private DBPrimaryKeyFactory()
    {
    }
    
    public static DBPrimaryKey DBPrimaryKeyFactory(ResultSet primaryKeysRS) throws Exception
    {
        String keyName = primaryKeysRS.getString(4);
        
        return new DBPrimaryKey(keyName);
    }
}
