package robot;

import emulator.cpu.ISynchronousUSART;
import emulator.cpu.PinConnector;
import emulator.cpu.IAsynchronousUSART;
/**
 * Base class representing a single robot part.
 * 
 * @author Sam Weiss 
 * @version 0.1.0
 */
public class Part extends PinConnector<Byte> implements ISynchronousUSART
{
    private int health;
    private int serialPort;
    protected Robot robot;
    protected IAsynchronousUSART device;
    protected String name;
    
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

    public int getHealth()
    {
        return this.health;
    }
    
    public void inflictDamage(int damage)
    {
        this.health = Math.max(0, this.health - damage);
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
    
    public void setDevice(IAsynchronousUSART device)
    {
        this.device = device;
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

    public Part setName(String name)
    {
        this.name = name;
        return this;
    }
    public String getName()
    {
        return this.name;
    }
    
    public void act()
    {
        if (this.hasNewData())
        {
            this.setData(this.TxRx(getData()));
        }
    }
    
    public void Rx(byte data)
    {
        this.device.Rx(this.TxRx(data));
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
