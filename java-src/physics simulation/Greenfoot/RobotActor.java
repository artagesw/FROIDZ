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
    
    private boolean flag = false;

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
            Projectile p = new Projectile(50, this.getRotation(), 1);
            ((Arena)this.getWorld()).add(p, 0, 0);
            this.shoot(p);
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
     * Direction of projectile must already be set
     */
    public void shoot(Projectile p)
    {
        p.setRotation(this.getRotation());
        
        p.setLocation(this.getState().getDisplacement().getI() + ((this.getImage().getHeight() + p.getImage().getHeight()) / 2 * Math.cos(p.getRotation()) + p.getBuffer()), 
                      this.getState().getDisplacement().getJ() + ((this.getImage().getHeight() + p.getImage().getHeight()) / 2 * Math.sin(p.getRotation()) + p.getBuffer()));
    }
}
