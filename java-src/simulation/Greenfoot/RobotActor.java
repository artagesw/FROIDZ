 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import robot.Robot;

/**
 * A stub class.
 * 
 * @author Haley B-E and Brendan Redmond
 * @version 0.1.0
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
<<<<<<< HEAD
        this.move(this.robot.getSpeed() * ArenaActor.ACT_TIME);
        this.turn(this.robot.getRotationalVelocity() * ArenaActor.ACT_TIME);
=======
>>>>>>> 7ff97435d97346f59b395c21e1ac9681e6d66a37
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
