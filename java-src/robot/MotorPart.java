package robot;


/**
 * Write a description of class MotorPart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MotorPart extends Part
{
    private int maxSpeed;   // in meters/sec.
    private int curSpeed;
    
    /**
     * Constructor for objects of class MotorPart
     */
    public MotorPart(int maxSpeed)
    {
        this.maxSpeed = 0;
        this.curSpeed = 0;
    }

    public byte TxRx(byte data)
    {
        this.curSpeed = data;
        this.robot.setSpeed(this.curSpeed/255*this.maxSpeed);
        return 0;
    }
}
