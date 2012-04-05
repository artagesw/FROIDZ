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
public class USART extends Peripheral implements IAsynchronousUSART
{   
    protected IAsynchronousUSART device;
    
    protected int udr;
    protected int ucsr;
    
    protected Queue<Character> buffer = new ConcurrentLinkedQueue();
    
    public USART(Memory mem, int udrAddress, int ucsrAddress)
    {
        super(mem);
        this.udr = udrAddress;
        this.ucsr = ucsrAddress;
    }
    
    public void connect(IAsynchronousUSART usart)
    {
        this.setDevice(usart);
        usart.setDevice(this);
    }
    
    public void setDevice(IAsynchronousUSART usart)
    {
        this.device = usart;
    }
    
    public synchronized void clock()
    {
        if ((this.mem.io[this.ucsr] & 32) == 0)
        {
            if (this.device == null)
            {
                return;
            }
            synchronized (this.device)
            {
                this.device.Rx((byte)this.mem.io[this.udr]);
                this.mem.io[this.ucsr] |= 32;
            }
        }   
    }
    
    public synchronized char read()
    {
        Character c = this.buffer.poll();
        if (this.buffer.peek() == null)
        {
            this.mem.io[this.ucsr] &= ~128;
        }
        if (c == null)
        {
            return 0;
        }
        return c;
    }
    
    public synchronized void Rx(byte data)
    {
        this.buffer.offer((char)data);
        this.mem.io[this.ucsr] |= 0x80;
    }
}
