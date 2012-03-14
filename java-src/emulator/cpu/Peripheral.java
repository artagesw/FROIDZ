/**
 * Write a description of class Peripheral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Peripheral
{
    // Alias of the cpu's io space
    protected char[] io;
    
    public Peripheral(char[] io)
    {
        this.io = io;
    }
    
    public Peripheral()
    {
    }
    
    // Do everything that this needs to do.
    public abstract void clock();
}
