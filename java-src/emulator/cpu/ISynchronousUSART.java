package emulator.cpu;


/**
 * Write a description of interface IUSART here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface ISynchronousUSART extends IAsynchronousUSART
{
    public abstract byte TxRx(byte b);
}
