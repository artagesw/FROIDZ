import java.util.HashMap;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Parses a wp file.
 * 
 * @author Jacob Weiss,Alex Teiche
 * @version 0.0.1
 */
public class WPParser
{
    private HashMap<String, WPChunk> chunkBag = new HashMap();

    //Public methods
    // get_opcode - Used by assember
    // get_pattern - Used by simulator

    public WPParser()
    {
        this("../../../dev/TOASTY Asm.wp");
    }
    
    public WPParser(String path)
    {
        int num = 0;
        
        for (String line : this.removeComments(this.getLines(path)))
        {
            WPChunk chunk = new WPChunk(line);
            chunkBag.put(chunk.getOpName(), chunk);
            num++;
        }
        
        System.out.println(num + " Instructions Supported.");
    }
    
    /**
     * Take an opcode including data and return the WPChunk representing the operation
     * @param s
     */
    public WPChunk search(String code)
    {
        for (WPChunk c : this.chunkBag.values())
        {
            if (c.match(code))
            {
                return c;
            }
        }
        
        return null;
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
            System.out.println("File not found.");
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
}
