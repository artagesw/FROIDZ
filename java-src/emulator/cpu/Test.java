package cpu; 

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
        FROIDZCPU p = new FROIDZCPU();
        
        p.act(3);
        
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).mem.sram[3]));
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).mem.io[0]));
    }
    
    public static void speedTest()
    {
        FROIDZCPU cpu = new FROIDZCPU();
        cpu.proc.mem.flash[0] = Integer.parseInt("01010011000000000000000000000000", 2);
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
    
    public static FROIDZCPU helloWorld()
    {
        FROIDZCPU cpu = new FROIDZCPU("/Users/alexteiche/Desktop/FROIDZ/java-src/emulator/cpu/helloworld.asm");
       
        return cpu;
    }
     
    public static void pinTest()
    {
        Pin<Boolean> p1 = new Pin(true);
        Pin<Boolean> p2 = new Pin(false);
        LED l1 = new LED();
        LED l2 = new LED();
        
        p1.connect(l1);
        p2.connect(l2);
        l1.connect(l2);
    }
    
    /*
    public static void addTest()
    {
        cpu.proc.flash[0] = 1124073729; // Put 1 into reg 1
        cpu.proc.flash[1] = 1124073986; // Put 2 into reg 2
        cpu.proc.flash[2] = 16974337; // Add reg 1 and reg 2, put result in reg 3
        this.act(3);
        System.out.println((int)proc.mem.registers[1] + " + " + (int)proc.mem.registers[2] + " = " + (int)proc.mem.registers[3]);
    }
    */
}
