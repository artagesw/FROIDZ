import java.util.Date;
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
        
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).mem.sram[3]));
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).mem.io[0]));
    }
    
    public static void speedTest()
    {
        FROIDCPU cpu = new FROIDCPU();
        long cycles = 0;
        Date t = new Date();
        long start = t.getTime();
        while(cycles < 600000000)
        {
            cpu.act(1000);
            cycles += 1000;
        }
        t = new Date();
        System.out.println((cycles / (t.getTime() - start)) / 1000 + " mHz");
    }
    
    /*
    public static void addTest()
    {
        this.proc.flash[0] = 1124073729; // Put 1 into reg 1
        this.proc.flash[1] = 1124073986; // Put 2 into reg 2
        this.proc.flash[2] = 16974337; // Add reg 1 and reg 2, put result in reg 3
        this.act(3);
        System.out.println((int)proc.mem.registers[1] + " + " + (int)proc.mem.registers[2] + " = " + (int)proc.mem.registers[3]);
    }
    */
}
