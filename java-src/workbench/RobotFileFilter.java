package workbench;
import java.io.*;

/**
 * Write a description of class RobotFileFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RobotFileFilter implements FileFilter
{
    private XMLFile playingRobots;
    
    public RobotFileFilter(File f)
    {
        this.playingRobots = new XMLFile(f);
    }
    
    public boolean accept(File f)
    {
        return (f.getName().endsWith(".xml") && !f.getName().equals("user.xml") && this.playingRobots.contains(f.getName()));
    }
}
