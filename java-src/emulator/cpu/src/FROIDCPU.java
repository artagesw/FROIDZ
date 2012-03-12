
public class FROIDCPU
{
    
    private ToastyProcessor proc;

    // How many cycles extra have been used
    // This is subtracted from the number total we can use
    private int cycleDebt = 0;
    
    public FROIDCPU()
    {
        this.proc = new ToastyProcessor(10, 10, 64, 64, 1);
        this.proc.flash[0] = 134348799;
        this.proc.flash[1] = 134348801;
        this.proc.flash[2] = 16974082;
    }
    
    /**
     * Tell the CPU to do things
     * @param time Milliseconds for the CPU to do things
     */
    public void act(int time)
    {
        // Execute a certain number of cycles on the processor
        this.cycleDebt = this.proc.run((time * proc.getClockSpeed()) - this.cycleDebt);
        System.out.println("Debt: " + cycleDebt);
        
    }
    
    // public getter methods
    
    public Processor getProcessor()
    {
        return this.proc;
    }
 
    
}
