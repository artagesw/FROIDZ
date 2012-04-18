package robot;


/**
 * Write a description of class LaunchAction here.
 * 
 * @author Sam Weiss
 * @version 0.1.0
 */
public class LaunchAction extends RobotAction
{
    private int kind;       // projectile type
    private double radius;  // radius of projectile in cm
    private double mass;    // mass of projectile in kg
    private double speed;   // launch speed in meters/sec.
    
    /**
     * Constructor for objects of class LaunchAction
     */
    public LaunchAction(int kind, double radius, double mass, double speed)
    {
        this.kind = kind;
        this.radius = radius;
        this.mass = mass;
        this.speed = speed;
    }
    
    public int getKind()
    {
        return this.kind;
    }
    
    public double getRadius()
    {
        return this.radius;
    }
    public double getMass()
    {
        return this.mass;
    }
    public double getSpeed()
    {
        return this.speed;
    }
}
