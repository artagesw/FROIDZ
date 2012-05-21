package robot;

/**
 * Write a description of class SpinPart here.
 * 
 * @author Jacob Weiss 
 * @version 0.0.1
 */
public class SpinPart extends Part
{
    private int maxRotV = 100; // Degrees/sec
    private double curRotV;
    
    public SpinPart()
    {
        this.curRotV = 0;
        this.name = "SpinPart";
        System.out.println("CREATE SPINPART");
    }
    
    public SpinPart setMaxRotationalVelocity(int max)
    {
        this.maxRotV = max;
        return this;
    }
    
    public byte TxRx(byte data)
    {
        System.out.println("SPIN " + data);
        this.curRotV = (0x00FF & (int)data);
        this.robot.setRotationalVelocity(this.curRotV*this.maxRotV/255);
        return 0;
    }
}
