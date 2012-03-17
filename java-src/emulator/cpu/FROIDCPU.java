public class FROIDCPU
{
    
    private ToastyProcessor proc;

    // How many cycles extra have been used
    // This is subtracted from the number total we can use
    private int cycleDebt = 0;
    
    public FROIDCPU()
    {
        this.proc = new ToastyProcessor(10, 10, 64, 64, 1);
        this.proc.flash[0] = 1124073729; // Put 1 into reg 1
        this.proc.flash[1] = 1124073986; // Put 2 into reg 2
        this.proc.flash[2] = 16974337; // Add reg 1 and reg 2, put result in reg 3
        this.act(3);
        System.out.println((int)proc.registers[1] + " + " + (int)proc.registers[2] + " = " + (int)proc.registers[3]);
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
