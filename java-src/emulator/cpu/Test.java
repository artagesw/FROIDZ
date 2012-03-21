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
        FROIDCPU p = new FROIDCPU();
        
        p.act(3);
        
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).mem.sram[3]));
        System.out.println((int)(((ToastyProcessor)(p.getProcessor())).mem.io[0]));
    }
    
    public static void speedTest()
    {
        FROIDCPU cpu = new FROIDCPU();
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
    
    public static FROIDCPU helloWorld()
    {
        FROIDCPU cpu = new FROIDCPU();
        
        cpu.proc.mem.flash[0] = Integer.parseInt("01000011000000000000000101001000", 2);
        cpu.proc.mem.flash[1] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[2] = Integer.parseInt("01000011000000000000000101100101", 2);
        cpu.proc.mem.flash[3] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[4] = Integer.parseInt("01000011000000000000000101101100", 2);
        cpu.proc.mem.flash[5] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[6] = Integer.parseInt("01000011000000000000000101101100", 2);
        cpu.proc.mem.flash[7] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[8] = Integer.parseInt("01000011000000000000000101101111", 2);
        cpu.proc.mem.flash[9] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[10] = Integer.parseInt("01000011000000000000000100101100", 2);
        cpu.proc.mem.flash[11] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[12] = Integer.parseInt("01000011000000000000000100100000", 2);
        cpu.proc.mem.flash[13] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[14] = Integer.parseInt("01000011000000000000000101010111", 2);
        cpu.proc.mem.flash[15] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[16] = Integer.parseInt("01000011000000000000000101101111", 2);
        cpu.proc.mem.flash[17] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[18] = Integer.parseInt("01000011000000000000000101110010", 2);
        cpu.proc.mem.flash[19] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[20] = Integer.parseInt("01000011000000000000000101101100", 2);
        cpu.proc.mem.flash[21] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[22] = Integer.parseInt("01000011000000000000000101100100", 2);
        cpu.proc.mem.flash[23] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[24] = Integer.parseInt("01000011000000000000000100001101", 2);
        cpu.proc.mem.flash[25] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[26] = Integer.parseInt("01000011000000000000000100001101", 2);
        cpu.proc.mem.flash[27] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[26] = Integer.parseInt("01000011000000000000000100001010", 2);
        cpu.proc.mem.flash[27] = Integer.parseInt("01000110000000001001110000000001", 2);
        cpu.proc.mem.flash[28] = Integer.parseInt("01010011000000000000000000000000", 2);
        
        return cpu;
    }
    
    public static void pinTest()
    {
        
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
