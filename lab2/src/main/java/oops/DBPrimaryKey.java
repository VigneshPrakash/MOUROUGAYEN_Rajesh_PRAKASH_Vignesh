package oops;

public class DBPrimaryKey 
{
    private String keyName;
    
    public DBPrimaryKey(final String keyName) throws Exception
    {
	this.keyName = keyName;	
    }
    
    public String toSQL() throws Exception
    {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.keyName);

        return sb.toString();
    }
    
    
}
