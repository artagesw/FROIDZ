import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import robot.Robot;
/**
 * A stub class.
 * 
 * @author Haley B-E and Brendan Redmond and Jacob Weiss
 * @version 0.3.0
 */
public class RobotActor extends ArenaActor
{
    private Robot robot;
    
    private boolean flag = true;

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

        this.state.setRotationalVelocity(this.robot.getRotationalVelocity());
        
        double direction = this.state.getOrientation() * Math.PI / 180;
        this.state.applyForce(new Vector(Math.cos(direction) * this.robot.getSpeed(), 
                                         Math.sin(direction) * this.robot.getSpeed()));
        super.act();

        if (flag)
        {
            this.shoot(100, 1, 5);
            flag = false;
        }
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
    
    /**
     * 
     */
    public void shoot(double speed, double mass, double radius)
    {
        double xChange = (((this.getState().getRadius() + radius) * Math.cos(this.state.getOrientation() * (Math.PI / 180)) + Projectile.BUFFER));
        double yChange = (((this.getState().getRadius() + radius) * Math.sin(this.state.getOrientation() * (Math.PI / 180)) + Projectile.BUFFER));
        
        double x = this.getState().getDisplacement().getI() + xChange;
        double y = this.getState().getDisplacement().getJ() + yChange;
        
        Vector velocity = new Vector(xChange, yChange);
        velocity.unitVector().scale(speed);
        
        Projectile p = new Projectile(velocity, mass, radius, x, y);
        
        ((Arena)this.getWorld()).add((ArenaActor)p, x, y);
    }
}