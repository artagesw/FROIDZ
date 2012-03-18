import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.util.Map.Entry;
/** Parser
 * 
 * Parses a def.inc file to create a java constant file.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class Parser
{   
    public void parse()
    {
        HashMap<String, Integer> lines = this.getMap("./m64def.inc");
        for (Entry<String, Integer> e : lines.entrySet())
        {
            System.out.println(e.getKey() + "->" + e.getValue());
        }
    }
    
    private HashMap<String, Integer> getMap(String path)
    {
        HashMap<String, Integer> lines = new HashMap();
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File(path));
        }
        catch (Throwable e)
        {
            System.out.println("404 FIle not found: " + path);
            return null;
        }
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if (line.indexOf(".equ") >= 0)
            {
                String[] parts = line.split("\\t| ");
                System.out.print(line);
                System.out.println(" " + parts.length);
                lines.put(parts[1], this.stringToInteger(parts[3], lines));
            }
        }
        return lines;
    }
    
    private Integer stringToInteger(String s, HashMap<String, Integer> lines)
    {
        try
        {
            if (s.indexOf("0x") >= 0)
            {
                return new Integer(Integer.parseInt(s.substring(2), 16));
            }
            return new Integer(Integer.parseInt(s));
        }
        catch (Throwable e)
        {
            return lines.get(s);
        }
    }
}
