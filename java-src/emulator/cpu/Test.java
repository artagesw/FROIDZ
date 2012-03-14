
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    public static void main()
    {
        FROIDCPU p = new FROIDCPU();
        
        p.act(3);
        
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).sram[3]));
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).io[0]));
    }
    
    public static void shiftest()
    {
        System.out.println((134283265 & 0xFF000000) >> 24);
        //System.out.println(0xFF & 0xF0);
    }
}
