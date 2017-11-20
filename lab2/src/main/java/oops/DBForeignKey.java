package oops;

public class DBForeignKey 
{
    private String primaryKeyName;
    private String foreignTableName;
    private String foreignKeyName;
    
    public DBForeignKey(final String primaryKeyName, final String foreignTableName,final String foreignKeyName) throws Exception
    {
    		this.primaryKeyName = primaryKeyName;	
    		this.foreignTableName = foreignTableName;
    		this.foreignKeyName = foreignKeyName;
    }
    
    public String toSQL() throws Exception
    {
        final StringBuffer sb = new StringBuffer();
        sb.append("\nCONSTRAINT fk_"+ this.primaryKeyName +"_"+ this.foreignTableName +" FOREIGN KEY (" + this.primaryKeyName + ") REFERENCES " 
        + this.foreignTableName + "(" + this.foreignKeyName + ")");

        return sb.toString();
    }
    
    
}
