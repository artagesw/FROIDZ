import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import robot.Robot;

/**
 * A stub class.
 * 
 * @author Haley B-E and Brendan Redmond
 * @version 0.2.0
 */
public class RobotActor extends ArenaActor
{
    private Robot robot;    

    public RobotActor(Robot robot)
    {
        super();
        
        this.robot = robot;
    }

    /**
     * Act - do whatever the RobotActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        this.robot.act(ArenaActor.ACT_TIME);
        this.bigMove(this.robot.getSpeed() * (ArenaActor.ACT_TIME/100.0));
        this.turn(this.robot.getRotationalVelocity() * ArenaActor.ACT_TIME);

    }    
    
    public double getMass()
    {
        return 0.0;
    }
    
    public boolean isInContact()
    {
        return (this.getOneIntersectingObject(ArenaActor.class) != null);
    }
    
    public void takeDamage(double damage)
    {
        
    }
    
    public double getHealth()
    {
        return 0.0;
    }
}
