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
    public static final int AVR_WORDS_PER_LINE = 8;
    private static final Binary EOF = new Binary("0x00000001FF");
    private static final Binary DATA_RECORD = new Binary("0x00");

    private WPParser parser;

    public ASMParser() throws InvalidInputException
    {
        this("../test.asm");
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
        
        System.out.println("GROUPING INSTRUCTIONS");
        insts = this.separateInstructions(insts);
        
        
        for (Object o : insts)
        {
            System.out.println(o);
        }
        System.out.println();
        
        System.out.println("GENERATING ADDRESSES AND LINE BYTE COUNTS");
        generateByteCountsAndAddresses(insts);
        
        for (Object o : insts)
        {
            System.out.println(o);
        }
        System.out.println();
        System.out.println("ASSEMBLY DONE");
        
        generateChecksums(insts);
        
        
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
                p.set(i, p.get(i).replace("R", ""));
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
     * Separates the instructions to wordsPerLine words per line
     * @param lines The lines of raw instructions, one instruction per line
     * @param wordsPerLine The number of words to put on one line in the hex file
     * @return List of lines, wordsPerLine words to a line
     */
    private List<Binary> separateInstructions(List<Binary> instructions, int wordsPerLine)
    {
        Iterator<Binary> it = instructions.iterator();
        
        List<Binary> out = new ArrayList();       
        
        while (it.hasNext())
        {
            Binary curLine = it.next();
            for(int i = 1; it.hasNext() && i < wordsPerLine; i++)
            {
                curLine.concatBack(it.next());
            }
            out.add(curLine);
        }
        
        return out;
    }
    
    private List<Binary> separateInstructions(List<Binary> instructions)
    {
        return separateInstructions(instructions, ASMParser.AVR_WORDS_PER_LINE);
    }
    
    private void generateByteCountsAndAddresses(List<Binary> lines)
    {
        Binary addr = new Binary("0x0000");
        
        for (Binary line : lines)
        {
            System.out.println(line + " " + line.getNumBits());
            Binary byteCount = new Binary("0b" + (Integer.toBinaryString(line.getNumBits() / 8)));
            line.concatFront(ASMParser.DATA_RECORD);
            line.concatFront(addr);
            line.concatFront(byteCount);
            addr.add(byteCount);
        }
        

    }
    
    private void generateChecksums(List<Binary> lines)
    {
        for(Binary b : lines)
        {
            b.concatFront(ASMParser.generateLineChecksum(b));
        }
    }
    
    public void addEOF(List<Binary> lines)
    {
        lines.add(ASMParser.EOF);
    }
    
    /**
     * Add the preceding colons to every line of Intel Hex and convert the binary objects to Strings
     * @param 
     */
    private List<String> finalize(List<String> lines)
    {
        List<String> l = new ArrayList();
        
        for (String line : lines)
        {
            l.add(":" + line);
        }
        
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
    
    public static Binary generateLineChecksum(Binary line)
    {
        Binary count = line.slice(0, 9);
        Binary addr = line.slice(9, 25);
        Binary type = line.slice(25, 34);
        Binary data = line.slice(34);
        
        System.out.println(count + " " + addr + " " + type + " " + data);
        
        return count;
    }
    
    public void test()
    {
        List<String> lines = new ArrayList();
        
        lines.add("0E");
        lines.add("DE");
        lines.add("FE");
        lines.add("4E");
        lines.add("5A");
        lines.add("9D");
        lines.add("QF");
        lines.add("82");
        lines.add("90");
        lines.add("50");
        lines.add("29");
        lines.add("40");
        lines.add("F9");
        lines.add("F1");
        lines.add("F2");
        lines.add("F3");
        lines.add("FF");
        lines.add("F4");
        lines.add("F5");
        lines.add("F6");
        lines.add("F7");
        lines.add("F8");
        lines.add("DD");
        lines.add("AC");
        lines.add("DC");
    
        //lines = separateInstructions(lines);
        
        for (String l : lines)
        {
            System.out.println(l);
        }
    }

}
