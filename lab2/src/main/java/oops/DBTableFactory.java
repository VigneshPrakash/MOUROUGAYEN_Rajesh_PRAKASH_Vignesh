package oops;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DBTableFactory
{ 
    private static ResultSet columnRS;
    private static ResultSet primaryKeysRS;
    private static ResultSet importedKeysRS;
    private static ResultSet uniqueKeysRS;
    
    private DBTableFactory()
    {
    }
    
    public static DBTable DBTableFactory(ResultSet table, DatabaseMetaData data) throws Exception
    {       
        String tableName = table.getString("TABLE_NAME");
        
        String catalog = null;//DB name
	String schema = null;//schema of the table
	String column = null;//columns name
        
        columnRS = data.getColumns(catalog, schema, tableName, column);
        primaryKeysRS = data.getPrimaryKeys(catalog, schema, tableName);
        importedKeysRS = data.getImportedKeys(catalog, schema, tableName);
        uniqueKeysRS = data.getIndexInfo(catalog, schema, tableName, true, true);

        return new DBTable(tableName, columnRS, primaryKeysRS, importedKeysRS, uniqueKeysRS);
    } 

    public static ResultSet getColumnRS() {
        return columnRS;
    }

    public static ResultSet getPrimaryKeysRS() {
        return primaryKeysRS;
    }

    public static ResultSet getImportedKeysRS() {
        return importedKeysRS;
    }

    public static ResultSet getUniqueKeysRS() {
        return uniqueKeysRS;
    }
    
    
}
