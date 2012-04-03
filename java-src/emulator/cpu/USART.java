package emulator.cpu; 

import emulator.wp.*; 
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Acts like a USART on an ATMEGA
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class USART extends Peripheral
{
    protected ArrayList<IUSART> devices = new ArrayList();
    
    protected int udr;
    protected int ucsr;
    
    protected Queue<Character> buffer = new ConcurrentLinkedQueue();
    
    public USART(Memory mem, int udrAddress, int ucsrAddress)
    {
        super(mem);
        this.udr = udrAddress;
        this.ucsr = ucsrAddress;
    }
    
    public void connect(IUSART usart)
    {
        this.devices.add(usart);
    }
    
    public void clock()
    {
        if ((this.mem.io[this.ucsr] & 32) == 0)
        {
            for (IUSART d : this.devices)
            {
                this.buffer.offer((char)d.TxRx((byte)this.mem.io[this.udr]));
                this.mem.io[this.ucsr] |= 128;
                this.mem.io[this.ucsr] |= 32;
            }
        }
    }
    
    public char read()
    {
        Character c = this.buffer.poll();
        if (c == null)
        {
            this.mem.io[this.ucsr] &= ~128;
            return 0;
        }
        return c;
    }
}
