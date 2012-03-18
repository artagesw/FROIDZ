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
        
        this.proc.flash[0] = Integer.parseInt("01000011000000000000000101001000", 2);
        this.proc.flash[1] = Integer.parseInt("01000110000000001001110000000001", 2); 
        this.proc.flash[2] = Integer.parseInt("01000011000000000000000101100101", 2);
        this.proc.flash[3] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[4] = Integer.parseInt("01000011000000000000000101101100", 2);
        this.proc.flash[5] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[6] = Integer.parseInt("01000011000000000000000101101100", 2);
        this.proc.flash[7] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[8] = Integer.parseInt("01000011000000000000000101101111", 2);
        this.proc.flash[9] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[10] = Integer.parseInt("01000011000000000000000100101100", 2);
        this.proc.flash[11] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[12] = Integer.parseInt("01000011000000000000000100100000", 2);
        this.proc.flash[13] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[14] = Integer.parseInt("01000011000000000000000101010111", 2);
        this.proc.flash[15] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[16] = Integer.parseInt("01000011000000000000000101101111", 2);
        this.proc.flash[17] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[18] = Integer.parseInt("01000011000000000000000101110010", 2);
        this.proc.flash[19] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[20] = Integer.parseInt("01000011000000000000000101101100", 2);
        this.proc.flash[21] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[22] = Integer.parseInt("01000011000000000000000101100100", 2);
        this.proc.flash[23] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[24] = Integer.parseInt("01000011000000000000000100001101", 2);
        this.proc.flash[25] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[26] = Integer.parseInt("01000011000000000000000100001101", 2);
        this.proc.flash[27] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[26] = Integer.parseInt("01000011000000000000000100001010", 2);
        this.proc.flash[27] = Integer.parseInt("01000110000000001001110000000001", 2);
        this.proc.flash[28] = Integer.parseInt("01010011000000000000000000000000", 2);
        
        this.act(1000);
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
