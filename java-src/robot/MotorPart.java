package robot;


/**
 * Write a description of class MotorPart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MotorPart extends Part
{
    private int maxSpeed = 120;   // in meters/sec.
    private int curSpeed;
    
    /**
     * Constructor for objects of class MotorPart
     */
    public MotorPart()
    {
        this.curSpeed = 0;
    }
    
    public int getMaxSpeed()
    {
        return this.maxSpeed;
    }

    public MotorPart setMaxSpeed(int maxSpeed)
    {
        this.maxSpeed = maxSpeed;
        return this;
    }
    
    public byte TxRx(byte data)
    {
<<<<<<< HEAD
        this.curSpeed = (int)data;
        this.robot.setSpeed(this.curSpeed*this.maxSpeed/255);
=======
        //System.out.println("data = " + data);
        this.curSpeed = (int)data;
        this.robot.setSpeed((this.curSpeed*this.maxSpeed)/255);
        //System.out.println((this.curSpeed*this.maxSpeed)/255);
>>>>>>> 9ecae7dacc17f024e0f0f8a3bf89f7347300e084
        return 0;
    }
}
