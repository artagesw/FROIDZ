/**
 * Write a description of class Peripheral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
