package robot;

import emulator.cpu.IUSART;
import emulator.cpu.PinConnector;

/**
 * Base class representing a single robot part.
 * 
 * @author Sam Weiss 
 * @version 0.1.0
 */
public class Part extends PinConnector<Byte> implements IUSART
{
    /**
     * Constructor for objects of class RobotPart
     */
    public Part()
    {
    }
    
    public void acct()
    {
        if (this.hasNewData())
        {
            this.setData(this.TxRx(getData()));
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
