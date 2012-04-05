package emulator.cpu;

/**
 * Write a description of class IAsynchronousUSART here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IAsynchronousUSART
{
    public void Rx(byte data);
    public void setDevice(IAsynchronousUSART usart);
}
