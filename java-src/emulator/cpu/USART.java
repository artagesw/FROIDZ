package emulator.cpu; 

import emulator.wp.*; 
import java.util.ArrayList;
/**
 * Acts like a USART on an ATMEGA
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class USART extends Peripheral
{
    private ArrayList<IUSART> devices = new ArrayList();
    
    private int udr = 0;
    private int ucsr = 1;
    
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
                this.mem.io[this.udr] = (char)d.TxRx((byte)this.mem.io[this.udr]);
            }
        }
        this.mem.io[this.ucsr] |= 32;
    }
}
