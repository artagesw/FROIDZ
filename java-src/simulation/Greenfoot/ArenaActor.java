import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Common elements of actors that act in the Arena.
 * 
 * @author Brendan Redmond and Haley B-E
 * @version 0.1.0
 */
abstract public class ArenaActor extends Actor
{
    //the current speed of this ArenaActor in cells per unit time
    private double speed;
    //the current location of the ArenaActor as a double as to prevent truncation in the 
    //ArenaActor's actual location in Arena
    private Location location;

    /**
     * Constructor: set speed to 0
     */
    public ArenaActor()
    {
        this.speed = 0;
        this.location = new Location();
    }
    
    /**
     * Constructor: set speed and direction to given values
     * 
     * @param speed     the given speed
     * @param direction the given direction
     */
    public ArenaActor(double speed, int direction)
    {
        this.speed = speed;
        this.setRotation(direction);
    }
    
    /**
     * Act - do whatever the ArenaActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    /**
     * Resolves collisions between this ArenaActor and any intersecting ArenaActors
     */
    private void resolveCollisions()
    {
        for (ArenaActor a : this.getIntersectingObjects(ArenaActor.class))
        {
            this.collideWith(a);
        }
    }
    
    /**
     * Deals collision damage to this ArenaActor and a given intersecting ArenaActor, and changes
     *  the velocities of these ArenaActors.
     *  
     * @param a     the intersecting ArenaActor
     */
    private void collideWith(ArenaActor a)
    {
        this.takeDamage(a);
        a.takeDamage(this);
        
        this.deflect(a);
        a.deflect(this);
    }
    
    //public getter methods
    
    public double getSpeed()
    {
        return this.speed;
    }
    
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
    
    /**
     * Increases the speed of this ArenaActor by a given amount
     * 
     * @param increase  the amount by which the speed of this ArenaActor will increase
     */
    public void increaseSpeed(double increase)
    {
        this.speed += increase;
    }
    
    /**
     * Returns the mass of this ArenaActor
     * 
     * @return  the mass of this ArenaActor
     */
    abstract public double getMass();
}
