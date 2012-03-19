/**
 * FROIDCPU
 * 
 * A FROIDCPU understands what it means to be an actor in a FROIDZ Simulation.
 * FROIDCPU's act method takes a time, which it converts into cycles and then
 * gives to the processor. The cycleDebt is kept track of here.
 * 
 * @author Jacob Weiss, Alex Teiche
 * @version 0.0.2
 */
public class FROIDCPU
{
    // This' processor
    private Processor proc;

    // How many cycles extra have been used
    // This is subtracted from the number total we can use
    private int cycleDebt = 0;
    
    public FROIDCPU()
    {
        Memory mem = new AVRMemory();
        this.proc = new ToastyProcessor(mem, 1, new USART(mem));
        
        this.proc.mem.flash[0] = Integer.parseInt("01000011000000000000000101001000", 2);
        this.proc.mem.flash[1] = Integer.parseInt("01000110000000001001110000000001", 2); 
        this.proc.mem.flash[2] = Integer.parseInt("01000011000000000000000101100101", 2);
        this.proc.mem.flash[3] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[4] = Integer.parseInt("01000011000000000000000101101100", 2);
        this.proc.mem.flash[5] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[6] = Integer.parseInt("01000011000000000000000101101100", 2);
        this.proc.mem.flash[7] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[8] = Integer.parseInt("01000011000000000000000101101111", 2);
        this.proc.mem.flash[9] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[10] = Integer.parseInt("01000011000000000000000100101100", 2);
        this.proc.mem.flash[11] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[12] = Integer.parseInt("01000011000000000000000100100000", 2);
        this.proc.mem.flash[13] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[14] = Integer.parseInt("01000011000000000000000101010111", 2);
        this.proc.mem.flash[15] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[16] = Integer.parseInt("01000011000000000000000101101111", 2);
        this.proc.mem.flash[17] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[18] = Integer.parseInt("01000011000000000000000101110010", 2);
        this.proc.mem.flash[19] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[20] = Integer.parseInt("01000011000000000000000101101100", 2);
        this.proc.mem.flash[21] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[22] = Integer.parseInt("01000011000000000000000101100100", 2);
        this.proc.mem.flash[23] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[24] = Integer.parseInt("01000011000000000000000100001101", 2);
        this.proc.mem.flash[25] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[26] = Integer.parseInt("01000011000000000000000100001101", 2);
        this.proc.mem.flash[27] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[26] = Integer.parseInt("01000011000000000000000100001010", 2);
        this.proc.mem.flash[27] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.mem.flash[28] = Integer.parseInt("01010011000000000000000000000000", 2);
    }
    
    /**
     * Tell the CPU to do things
     * @param time Milliseconds for the CPU to do things
     */
    public void act(int time)
    {
        // Execute a certain number of cycles on the processor
        this.cycleDebt = this.proc.run((time * proc.getClockSpeed()) - this.cycleDebt);
    }
    
    // public getter methods
    
    public Processor getProcessor()
    {
        return this.proc;
    } 
}
