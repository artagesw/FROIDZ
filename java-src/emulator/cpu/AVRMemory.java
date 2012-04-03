package emulator.cpu; 
 
import emulator.wp.*;

/**
 * AVRMEMORY
 * 
 * Represents the memory of an AVR Microcontroller device.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class AVRMemory extends Memory
{
    private Peripheral[] peripheralMap;
    /**
     * Constructor for objects of class AVRMemory
     */
    public AVRMemory()
    {
        super(32, 256, 4096, 65535);
    }
    
    public void setPeripheralMap(Peripheral[] p)
    {
        this.peripheralMap = p;
    }
    
    // Override methods ro react to different addresses being written to.
    public void writeIO(int addr, char data)
    {
        switch (addr)
        {
            case ToastyIO.UDR0:
            {
                this.io[ToastyIO.UCSR0A] &= ~32;
            }
            case ToastyIO.UDR1:
            {
                this.io[ToastyIO.UCSR1A] &= ~32;
            }
            case ToastyIO.UDR2:
            {
                this.io[ToastyIO.UCSR2A] &= ~32;
            }
            case ToastyIO.UDR3:
            {
                this.io[ToastyIO.UCSR3A] &= ~32;
            }
            case ToastyIO.UDR4:
            {
                this.io[ToastyIO.UCSR4A] &= ~32;
            }
            case ToastyIO.UDR5:
            {
                this.io[ToastyIO.UCSR5A] &= ~32;
            }
            case ToastyIO.UDR6:
            {
                this.io[ToastyIO.UCSR6A] &= ~32;
            }
            case ToastyIO.UDR7:
            {
                this.io[ToastyIO.UCSR7A] &= ~32;
            }
        }
        super.writeIO(addr, data);
    }
    public char readIO(int addr)
    {
        switch (addr)
        {
            case ToastyIO.UDR0:
            {
                this.io[ToastyIO.UDR0] = ((USART)this.peripheralMap[ToastyIO.UDR0]).read();
            }
            case ToastyIO.UDR1:
            {
                this.io[ToastyIO.UDR1] = ((USART)this.peripheralMap[ToastyIO.UDR1]).read();
            }
            case ToastyIO.UDR2:
            {
                this.io[ToastyIO.UDR2] = ((USART)this.peripheralMap[ToastyIO.UDR2]).read();
            }
            case ToastyIO.UDR3:
            {
                this.io[ToastyIO.UDR3] = ((USART)this.peripheralMap[ToastyIO.UDR3]).read();
            }
            case ToastyIO.UDR4:
            {
                this.io[ToastyIO.UDR4] = ((USART)this.peripheralMap[ToastyIO.UDR4]).read();
            }
            case ToastyIO.UDR5:
            {
                this.io[ToastyIO.UDR5] = ((USART)this.peripheralMap[ToastyIO.UDR5]).read();
            }
            case ToastyIO.UDR6:
            {
                this.io[ToastyIO.UDR6] = ((USART)this.peripheralMap[ToastyIO.UDR6]).read();
            }
            case ToastyIO.UDR7:
            {
                this.io[ToastyIO.UDR7] = ((USART)this.peripheralMap[ToastyIO.UDR7]).read();
            }
        }
        return super.readIO(addr);
    }
}
