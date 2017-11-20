package oops;

public class DBFileFactory 
{
    private DBFileFactory()
    {
    }
    
    public static DBFile DBFileFactory(String instructions) throws Exception
    {
        return new DBFile(instructions);
    }
}
