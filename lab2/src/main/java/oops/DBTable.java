package oops;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DBTable 
{
    private List<DBColumn> columns = new LinkedList<DBColumn>();
    private List<DBPrimaryKey> primaryKeys = new LinkedList<DBPrimaryKey>();
    private List<DBUniqueKey> uniqueKeys = new LinkedList<DBUniqueKey>();
    private List<DBForeignKey> foreignKeys = new LinkedList<DBForeignKey>();
   
    private String tableName;
    private ResultSet columnRS;
    private ResultSet primaryKeysRS;
    private ResultSet foreignKeysRS;
    private ResultSet uniqueKeysRS;
    private DBColumn dbc;
    private DBPrimaryKey pk;
    private DBUniqueKey uk;
    private DBForeignKey fk;
    
    protected DBTable(final String tableName, final ResultSet columnRS, final ResultSet primaryKeysRS, final ResultSet importedKeysRS, final ResultSet uniqueKeysRS) throws Exception
    {
        this.tableName = tableName;
        this.columnRS = columnRS;
        this.foreignKeysRS = importedKeysRS;
        this.uniqueKeysRS = uniqueKeysRS;
        this.primaryKeysRS = primaryKeysRS;

        while(columnRS.next())
        {
            dbc = DBColumnFactory.DBColumnFactory(columnRS);
            columns.add(dbc);
        }       
        while(primaryKeysRS.next())
        {
            pk = DBPrimaryKeyFactory.DBPrimaryKeyFactory(primaryKeysRS);
            primaryKeys.add(pk);
        }
        while(uniqueKeysRS.next())
        {
            uk = DBUniqueKeyFactory.DBUniqueKeyFactory(uniqueKeysRS);
            uniqueKeys.add(uk);
        }
        while(foreignKeysRS.next())
        {
            fk = DBForeignKeyFactory.DBForeignKeyFactory(foreignKeysRS);
            foreignKeys.add(fk);
        }
    }
      
    public String toSQL() throws Exception
    {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n\nCREATE TABLE " + this.tableName + "(");

        for(int i=0; i<columns.size(); i++)
        {
            sb.append(columns.get(i).toSQL());
        }
        
        
        
        if (primaryKeys.size()>0) {
	        sb.append("\nPRIMARY KEY (");
	        
	        for(int j=0; j < primaryKeys.size(); j++)
	        {
	            sb.append(primaryKeys.get(j).toSQL());
	            
	            if(j != (primaryKeys.size() - 1 )) {
	            		sb.append(", ");
	            }
	        }
	        
	        sb.append(")");
        }
        
        if (uniqueKeys.size()>0) {
	        sb.append(",\nUNIQUE KEY (");
	        
	        for(int k=0; k < uniqueKeys.size(); k++)
	        {
	            sb.append(uniqueKeys.get(k).toSQL());
	            
	            if(k != (uniqueKeys.size() - 1 )) {
	            		sb.append(", ");
	            }
	        }
	        
	        sb.append(")");
        }
        if (foreignKeys.size()>0) {
	        sb.append(",");
	        
	        for(int k=0; k < foreignKeys.size(); k++)
	        {
	            sb.append(foreignKeys.get(k).toSQL());
	            
	            if(k != (foreignKeys.size() - 1 )) {
	            		sb.append(", ");
	            }
	        }
	        
        }    
        
        sb.append("\n);");
        
        return sb.toString();
    }

    public List<DBColumn> getColumns() {
        return columns;
    }

    public String getTableName() {
        return tableName;
    }

    public ResultSet getColumnRS() {
        return columnRS;
    }

    public ResultSet getPrimaryKeysRS() {
        return primaryKeysRS;
    }

    public ResultSet getImportedKeysRS() {
        return foreignKeysRS;
    }

    public ResultSet getUniqueKeysRS() {
        return uniqueKeysRS;
    }

    public DBColumn getDbc() {
        return dbc;
    }
    
    
}
