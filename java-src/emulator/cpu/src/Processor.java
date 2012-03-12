
/**
 * Abstract class Processor - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Processor
{
    // Specified in kHz
    private final int CLOCK_SPEED;
    
    public Processor(int clockSpeed)
    {
        this.CLOCK_SPEED = clockSpeed;
    }
    
    // Public getter methods
    public int getClockSpeed()
    {
        return this.CLOCK_SPEED;
    }
    
    /**
     * Run for a given number of cycles
     * @return int -> 0, used all instructions. < 0 is how many extra cycles were used, and can't use next time.
     */
    public int run(int cycles)
    {
        System.out.println("Executing " + cycles + " cycles.");
        
        while (cycles > 0)
        {
            cycles -= this.execute();
        }
        
        return cycles * -1;
    }
    
    /**
     * Do one instruction
     * @return int -> The number of cycles it did
     */
    protected abstract int execute();
}
