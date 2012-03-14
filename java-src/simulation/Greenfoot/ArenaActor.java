import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Common elements of actors that act in the Arena.
 * 
 * @author Brendan Redmond and Haley B-E
 * @version 0.0.1
 */
abstract public class ArenaActor extends Actor
{
    //the current velocity of this ArenaActor
    private Velocity velocity;
    
    //how much damage is done by this ArenaActor when it collides with another actor - currently constant
    private int damage;

    /**
     * Constructor: set velocity to null
     */
    public ArenaActor()
    {
        this.velocity = null;
    }
    
    /**
     * Constructor: set velocity to a given velocity
     */
    public ArenaActor(Velocity velocity)
    {
        this.velocity = velocity;
    }
    
    /**
     * Constructor: set velocity based on given speed and direction
     */
    public ArenaActor(int speed, int direction)
    {
        this.velocity = new Velocity(speed, direction);
    }
    
    
    /**
     * Act - do whatever the ArenaActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    //public getter methods
    
    public Velocity getVelocity()
    {
        return this.velocity;
    }
    
    public int getSpeed()
    {
        return this.velocity.getSpeed();
    }
    
    public int getDirection()
    {
        return this.velocity.getDirection();
    }
    
    public int getDamageDone()
    {
        return this.damage;
    }
}
