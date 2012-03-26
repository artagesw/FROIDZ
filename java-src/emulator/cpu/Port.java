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
    private Pin[] pins;
    private String name;
    
    public Port(String name, int numPins)
    {
        this.pins = new Pin[numPins];
        this.name = name;
        
        for (int i = 0; i < numPins; i++)
        {
            //this.pins[i] = new Pin<Boolean>(false);
        }
    }
    
    public void clock()
    {
    }
}
