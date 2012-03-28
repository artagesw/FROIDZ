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
    protected Robot robot;
    private int serialPort;
    
    /**
     * Constructor for objects of class RobotPart
     */
    public Part()
    {
        this.robot = null;
        this.serialPort = 0;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  portNum  serial port to connect to
     */
    public Part setRobot(Robot robot)
    {
        this.robot = robot;
        return this;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  portNum  serial port to connect to
     */
    public Part setSerialPort(int portNum)
    {
        this.serialPort = portNum;
        return this;
    }

    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  portNum  serial port to connect to
     */
    public int getSerialPort()
    {
        return this.serialPort;
    }

    public void act()
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
