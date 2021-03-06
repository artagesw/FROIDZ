package emulator.wp; 

import java.util.HashMap;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Parses a wp file.
 * 
 * @author Jacob Weiss,Alex Teiche
 * @version 0.0.1
 */
public class WPParser
{
    private HashMap<String, WPChunk> chunkBag = new HashMap();

    private String WPFile;
    
    
    //Public methods
    // get_opcode - Used by assember
    // get_pattern - Used by simulator

    public WPParser()
    {
        int num = 0;
        
        String pathJacob = new String("/Users/jacob/Stacks/Dropbox/12th/APCS/FROIDZ/FROIDZ/java-src/emulator/dev/TOASTY Asm.wp");
        String pathAlex = new String("/Users/alexteiche/Desktop/FROIDZ/java-src/emulator/dev/TOASTY Asm.wp");
        
        List<String> lines = new ArrayList();
        
        lines = this.getLines(pathJacob);

        if (lines == null)
        {
            System.out.println("Using alex's path");
            lines = this.getLines(pathAlex);
        }
        
        this.parseLines(lines);  
    }
    
    public WPParser(String path)
    {
        this.parseLines(this.getLines(path));
    }
    
    private void parseLines(List<String> lines)
    {
        int num = 0;
        for (String line : this.removeComments(lines))
        {
            WPChunk chunk = new WPChunk(line);
            chunkBag.put(chunk.getOpName(), chunk);
            num++;
        }
        
        System.out.println(num + " Instructions Supported.");
    }
    
    public List<String> getLines(String path)
    {
        LinkedList<String> lines = new LinkedList();
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File(path));
            while (scanner.hasNextLine())
            {
                lines.add(scanner.nextLine().trim());
            }
        }
        catch (Throwable e)
        {
            System.out.println("404 File not found: " + path);
            return null;
        }
        return lines;
    }
    
    private List<String> removeComments(List<String> lines)
    {
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext())
        {
            String n = iterator.next();
            if (n.indexOf("#") == 0 || n.equals(""))
            {
                iterator.remove();
            }
        }
        return lines;
    }
    
    // Public Getter Methods
    
    public WPChunk getChunk(String name)
    {
        return this.chunkBag.get(name);
    }
    
    public void printAllInstructions()
    {
        for (WPChunk c : this.chunkBag.values())
        {
            System.out.println(c.getOpName() + " " + c.getOperands() + " " + c.getRanges() + " " + c.getOpCode());
        }
    }
}
