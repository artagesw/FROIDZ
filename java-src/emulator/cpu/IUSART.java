package emulator.cpu;

/**
 * Anything that can be connected to a USART Port is USARTAble
 * 
 * @author Alex Teiche, Jacob Weiss
 * @version (a version number or a date)
 */
public interface IUSART
{
    public byte TxRx(byte b);
}
