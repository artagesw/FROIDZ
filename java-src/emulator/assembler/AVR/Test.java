import java.util.ArrayList;
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    public static void testInstructionGeneration() throws InvalidInputException
    {
            WPChunk c = new WPChunk("mov | Rd=mnopq,Rr=abcde  | 0-Rd-31,0-Rr-31  | 0010 11am nopq bcde");
            ArrayList<Binary> l = new ArrayList();
            l.add(new Binary("0b11111"));
            l.add(new Binary("0b11100000"));
            System.out.println(c.generateInstruction(l).toBinaryString());
    }
    
    public static void testAdd()
    {
        Binary a = new Binary("0b1111");
        Binary b = new Binary(   "0b1");
        
        a.add(b);
        
        System.out.println(a.toBinaryString());
    }
    
    public static void testSplit()
    {
        Binary a = new Binary("0b01111000");
        System.out.println(a.split(0, 5).toBinaryString());
    }
    
    public static void testConcat()
    {
        ArrayList<Binary> l = new ArrayList();
        Binary b = new Binary();
        Binary b0 = new Binary("0xFFFF");
        Binary b1 = new Binary("0xABCD");
        l.add(b0);
        l.add(b1);
        
        for (Binary bin : l)
        {
            b.concatBack(bin);
        }

        System.out.println(b);
    }
    
    public static void testBla()
    {
        System.out.println(Integer.parseInt("GG", 17));
    }
}
