package oops;

import java.sql.ResultSet;

public class DBUniqueKeyFactory 
{
    private DBUniqueKeyFactory()
    {
    }
    
    public static DBUniqueKey DBUniqueKeyFactory(ResultSet uniqueKeysRS) throws Exception
    {
        String keyName = uniqueKeysRS.getString(9);
        return new DBUniqueKey(keyName);
    }
}
