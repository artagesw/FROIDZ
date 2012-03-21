package cpu;
/**
 * Peripherals of a microcontroller react to registers being written to and can act
 * once per instruction.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public abstract class Peripheral
{
    // Alias of the cpu's memory
    protected Memory mem;
    
    public Peripheral(Memory mem)
    {
        this.mem = mem;
    }
    
    public Peripheral()
    {
    }
    
    // Do everything that this needs to do.
    public abstract void clock();
}
