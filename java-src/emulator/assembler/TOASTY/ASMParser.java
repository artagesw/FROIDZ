import java.util.Scanner;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;  

/**
 * Write a description of class ASMParser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ASMParser 
{
    private WPParser parser;

    public ASMParser() throws InvalidInputException
    {
        this("./test.asm");
    }
    
    /**
     * Create an assembly parser given a path to an assembly file
     * @param path The path to the file
     */
    public ASMParser(String path) throws InvalidInputException
    {
        LinkedList<String> lines = new LinkedList();
        
        try
        {
            Scanner scanner = new Scanner(new File(path));

            while (scanner.hasNextLine())
            {
                lines.add(scanner.nextLine());
            }
        }
        catch (Throwable e)
        {
            System.out.println("404 File Not Found");
        }
        
        this.parser = new WPParser();
        
        List<Binary> insts = new ArrayList();
        List<List<String>> parsed = new ArrayList();
        
        System.out.println("FORMATTING ASSEMBLY FILE");
        this.format(lines);
        
        System.out.println("PREPROCESSING");
        parsed = this.preprocess(lines);

        for (Object o : lines)
        {
            System.out.print(o + " ");
        }
        System.out.println();
        
        System.out.println("GENERATING INSTRUCTIONS");
        insts = this.generateInstructions(parsed);
        
        for (Object o : insts)
        {
            System.out.print(o);
        }
        System.out.println();
        
      
        System.out.println();
        System.out.println("ASSEMBLY DONE");
        
        
        
        //this.generateInstructions(this.preprocess(this.format(lines)));
    }
    
    /**
     * Remove all newlines
     */
    private void format(List<String> lines)
    {
        Iterator<String> i = lines.iterator();
        String line;
        
        while (i.hasNext())
        {
            line = i.next().trim();
            if (line.length() == 0)
            {
                i.remove();
            }
        }
        
    }
    
    private List<List<String>> preprocess(List<String> lines)
    {
        List<List<String>> processed = new ArrayList();
        
        for (String l : lines)
        {
            List<String> p = this.parseLine(l);
            for (int i = 1; i < p.size(); i++)
            {
                p.set(i, p.get(i).replace("R", "0d"));
            }
            processed.add(p);
        }
        
        return processed;  
    }
    
    private List<Binary> generateInstructions(List<List<String>> lines) throws InvalidInputException
    {
        ArrayList<Binary> instructions = new ArrayList();
        
        for (List<String> parsed : lines)
        {
            WPChunk op = this.parser.getChunk(parsed.get(0));
            
            List<Binary> operands = this.makeBinariesFromOperands(parsed.subList(1, parsed.size()));
            
            instructions.add(op.generateInstruction(operands));
        }
        
        return instructions;    
    }
    
    /**
     * Add the preceding colons to every line of Intel Hex and convert the binary objects to Strings
     * @param 
     */
    private List<String> finalize(List<String> lines)
    {
        List<String> l = new ArrayList();
        
        // Do stuff that might be necessary to finalize the binary
        
        return l;
    }
 
    public static List<String> parseLine(String line)
    {
        List<String> parsed = new ArrayList();
        
        for (String s : line.replace(",", " ").split(" "))
        {
            if (s.length() != 0)
            {
                parsed.add(s);
            }
        }
       
        return parsed;

    }
    
    public List<Binary> makeBinariesFromOperands(List<String> ops)
    {
        List<Binary> bins = new ArrayList();
        
        for (String s : ops)
        {
            bins.add(new Binary(s));
        }
        
        return bins;
    }
}
