package emulator.cpu;


/**
 * Write a description of class PinPrinter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PinPrinter extends PinConnector<Byte>
{
    public void clock()
    {
        if (this.hasNewData())
        {
            System.out.println((char)(byte)this.getData());
        }
    }
}
