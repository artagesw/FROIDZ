package cpu; 

import wp.*; 
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
    
    public USART(Memory mem)
    {
        super(mem);
    }
    
    public void clock()
    {
        if ((this.mem.io[IO.UCSR1A] & 32) == 0)
        {
            for (IUSART d : this.devices)
            {
                this.mem.io[IO.UDR1] = (char)d.TxRx((byte)this.mem.io[IO.UDR1]);
            }
        }
        this.mem.io[IO.UCSR1A] |= 32;
    }
}
