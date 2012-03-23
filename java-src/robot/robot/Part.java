package robot;

import emulator.cpu.IUSART;

/**
 * Base class representing a single robot part.
 * 
 * @author Sam Weiss 
 * @version 0.1.0
 */
public class Part extends emulator.cpu.PinConnector<Byte>, implements emulator.cpu.IUSART
{
    /**
     * Constructor for objects of class RobotPart
     */
    public Part()
    {
    }
    
    public act()
    {
        if (this.hasNewData())
        {
            this.setMyData(this.TxRx(getMyData()));
        }
    }

    /**
     * Method TxRx
     * 
     * Transmit and/or receive serial data to/from CPU.
     *
     * @param data A single byte of data
     * @return A single byte of data
     */
    public byte TxRx(byte data)
    {
        return 0;
    }
}
