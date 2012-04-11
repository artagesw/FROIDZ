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
        this.robot.act(ArenaActor.ACT_TIME);
        this.state.setOrientation(this.robot.getRotationalVelocity() * this.ACT_TIME + this.state.getOrientation());
        double direction = this.state.getOrientation() * Math.PI / 180;
        this.state.applyForce(new Vector(Math.cos(direction) * this.robot.getSpeed(), 
                                         Math.sin(direction) * this.robot.getSpeed()));
        super.act();
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
