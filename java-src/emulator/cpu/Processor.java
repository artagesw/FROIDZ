package cpu; 
 
/**
 * Processor
 * 
 * Processors know how to run for a given number of clock cycles.
 * They are also in charge of remembering their clock speed.
 * 
 * @author Alex Teiche, Jacob Weiss
 * @version 0.0.1
 */
public abstract class Processor
{
    public Memory mem; // REMOVE THIS ONCE MEMORY BECOMES PRIVATE

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
        while (cycles > 0)
        {
            cycles -= this.execute();
        }
        
        return cycles * -1;
    }
    
    /**
     * Do one instruction
     * @return int -> The number of cycles that instruction took
     */
    protected abstract int execute();
}
