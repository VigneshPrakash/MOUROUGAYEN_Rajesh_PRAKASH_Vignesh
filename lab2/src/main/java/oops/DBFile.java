package oops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.log4j.Logger;

public class DBFile 
{
    private static File f;
    private static FileWriter fw;
    private static BufferedWriter bw;
    final static Logger logger= Logger.getLogger(DBFile.class);
    
    public DBFile(String instructions) throws Exception
    {
        f = new File("DMLInstructions.sql");
        logger.info("File has Created");
        if(f.exists())
        {
            f.delete();
            logger.info("Delete the previous version of the file");
        }
        fw = new FileWriter(f.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(instructions);
        bw.close();//To save every change
 
    }
}
