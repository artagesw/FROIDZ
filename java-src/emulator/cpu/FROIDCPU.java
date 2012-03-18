public class FROIDCPU
{
    
    private ToastyProcessor proc;

    // How many cycles extra have been used
    // This is subtracted from the number total we can use
    private int cycleDebt = 0;
    
    public FROIDCPU()
    {
        Memory mem = new AVRMemory();
        this.proc = new ToastyProcessor(mem, 100, 1, new USART(mem));
        
        this.proc.flash[0] = 1124073792; 
        this.proc.flash[1] = 1174445057;
        
        this.act(3);
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
