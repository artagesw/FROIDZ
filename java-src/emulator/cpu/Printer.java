package emulator.cpu;


/**
 * Write a description of class Printer here.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class Printer implements IUSART
{
    public byte TxRx(byte b)
    {
        System.out.println("NEW DATA" + (int)b);
        return 0;
    }
}
