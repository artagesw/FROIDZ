package workbench;
import java.io.*;

/**
 * Write a description of class FolderOnlyFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FolderOnlyFilter implements FileFilter
{
    public boolean accept(File f)
    {
        return f.isDirectory();
    }
}
