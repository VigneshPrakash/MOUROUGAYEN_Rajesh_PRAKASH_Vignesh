package oops;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DBBase 
{ 
    private List<DBTable> tables = new LinkedList<DBTable>();
    private String dbName;
    private ResultSet tableRS;
    private DatabaseMetaData data;
    private DBTable dbt;
    
    protected DBBase(final String dbName, final ResultSet tableRS, DatabaseMetaData data) throws Exception
    {
        this.dbName = dbName;
        this.tableRS = tableRS;
        this.data = data;
        
        while(this.tableRS.next())
        {
            dbt = DBTableFactory.DBTableFactory(this.tableRS, this.data);  
            tables.add(dbt);
        }
        //ALL DB FULFILLED
    }        
    
    public String toSQL() throws Exception
    {
        final StringBuffer sb = new StringBuffer();
        sb.append("\nCREATE DATABASE " + this.dbName + ";");

        for(int i=0; i<tables.size(); i++)
        {
            sb.append(tables.get(i).toSQL());
        }
        
        return sb.toString();
    }

    public List<DBTable> getTables() {
        return this.tables;
    }

    public String getDbName() {
        return this.dbName;
    }

    public ResultSet getTableRS() {
        return this.tableRS;
    }

    public DatabaseMetaData getData() {
        return this.data;
    }

    public DBTable getDbt() {
        return this.dbt;
    }
    
    
    
  
}
