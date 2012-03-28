package emulator.cpu;

import java.util.ArrayList;

/**
 * A Port is a collection of Pins tied to an io register.
 * 
 * @author Jacob Weiss 
 * @version 0.0.1
 */
public class Port extends Peripheral
{
    private Pin<Boolean>[] pins;
    
    private int port;
    private int pin;
    private int ddr;
    
    public Port(Memory mem, int numPins, int portAddress, int pinAddress, int ddrAddress)
    {
        super(mem);
        
        this.pins = new Pin[numPins];
        
        this.port = portAddress;
        this.ddr = ddrAddress;
        this.pin = pinAddress;
        
        for (int i = 0; i < numPins; i++)
        {
            this.pins[i] = new Pin<Boolean>(false);
        }
    }
    
    public void connect(Connector<Boolean> c, int pinNum)
    {
        this.pins[pinNum].connect(c);
    }
    
    public void clock()
    {
        for (int i = 0; i < this.pins.length; i++)
        {
            if ((this.mem.io[this.ddr] & (1 << i)) != 0)
            {
                this.pins[i].setValue((this.mem.io[this.port] & (1 << i)) != 0);
            }
            else
            {
                // Get input from pin. Needs implementation;.
            }
        }
    }
}
