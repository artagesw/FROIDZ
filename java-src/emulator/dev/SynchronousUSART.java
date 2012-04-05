package emulator.cpu;

import java.util.ArrayList;
/**
 * Write a description of class SynchronousUSART here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SynchronousUSART extends USART
{
    private ISynchronousUSART device;

    public SynchronousUSART(Memory mem, int udrAddress, int ucsrAddress)
    {
        super(mem, udrAddress, ucsrAddress);
    }
    
    public void connect(ISynchronousUSART usart)
    {
        this.device = usart;
    }
    
    public void clock()
    {
        if ((this.mem.io[this.ucsr] & 32) == 0)
        {
            this.buffer.offer((char)this.device.TxRx((byte)this.mem.io[this.udr]));
            this.mem.io[this.ucsr] |= 128;
            this.mem.io[this.ucsr] |= 32;
        }
    }
}
