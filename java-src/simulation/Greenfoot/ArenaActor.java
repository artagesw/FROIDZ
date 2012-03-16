import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Common elements of actors that act in the Arena.
 * 
 * 
 * @author Brendan Redmond and Haley B-E
 * @version 0.2.0
 */
abstract public class ArenaActor extends Actor
{
    //the current speed of this ArenaActor in cells per unit time
    private double speed;
    //the current acceleration of this ArenaActor in cells per unit time squared
    private double acceleration;
    
    //the current location of the ArenaActor as a double as to prevent truncation in the 
    //ArenaActor's actual location in Arena
    private Location location;

    /**
     * Constructor: set speed to 0
     */
    public ArenaActor()
    {
        this.speed = 0;
        this.acceleration = 0;
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
        assert(speed >= 0);
        assert(Math.abs(direction) < 360);
        assert(Math.abs(direction) > 0);
        
        this.speed = speed;
        this.setRotation(direction);
    }
    
    /**
     * Act - do whatever the ArenaActor wants to do. 
     * (To be overridden by subclasses)
     * 
     */
    public void act() 
    {       
        this.moveExactly(5.0);
        this.resolveCollisions();
    }   
    
    /**
     * Overrides greenfoot's setLocation method so it also changes the actor's stored exact location
     * @param x     x-coordinate of new location
     * @param y     y-coordinate of new location
     * 
     */
    public void setLocation(int x, int y)
    {
        super.setLocation(x, y);
        this.location.setX(x);
        this.location.setY(y);
    }
    
    
    /**
     * Moves the ArenaActor exactly the distance passed and updates actor's visual position to the closest approximate
     *          position
     * @param distance          the distance to move the actor
     */
    public void moveExactly(double distance)
    {
        double x, y;
        
        //finds new position in x-direction
        if ((this.getRotation() < 90) || (this.getRotation() > 270))
        {
          y = (this.location.getY() - (distance * Math.cos(this.getRotation())));
        }
        else
        {
            y = (this.location.getY() + (distance * Math.cos(this.getRotation())));
        }
        
        //finds new position in y-direction
        if (this.getRotation() < 180)
        {
            x = (this.location.getX() + (distance * Math.sin(this.getRotation())));
        }
        else
        {
            x = (this.location.getX() + (distance * Math.sin(this.getRotation())));
        }
        
        //checks to make sure we're not out of bounds - TEMPORARY UNTIL WE DECIDE ON BOUNDARY OBJECT
        if (x >= getWorld().getWidth())
        {
            x = getWorld().getWidth() - 1;
        }
        else if (x < 0)
        {
            x = 0;
        }
        if (y >= getWorld().getHeight())
        {
            y = getWorld().getHeight() - 1;
        }
        else if (y < 0)
        {
            y = 0;
        }
        
        this.location.setX(x);
        this.location.setY(y);
        
        moveTo((int)x, (int)y);
    }
    
    public boolean moveTo(int xNew, int yNew)
    {
        int xOld = this.getX();
        int yOld = this.getY();
        
        if (((xNew <= xOld + 1) && (xNew >= xOld - 1)) || ((yNew <= yOld + 1) && (yNew >= yOld - 1)))
        {
            this.setLocation(xNew, yNew);
            return true;
        }
        
        double slope = ((yNew - yOld) / (xNew - xOld));
        double b = yOld - (slope * xOld);
        
        for (int i = xOld; i < xNew; i++)
        {
            //this.setLocation(i, (int)(slope * i + b));
            this.move(1);
            this.resolveCollisions();
        }
        
        return true;

    }

    
    /**
     * Resolves collisions between this ArenaActor and any intersecting ArenaActors
     */
    private void resolveCollisions()
    {
        List<ArenaActor> actors = this.getIntersectingObjects(ArenaActor.class);
        
        for (ArenaActor a : actors)
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
        this.takeCollisionDamage(a);
        a.takeCollisionDamage(this);
        
        this.deflect(a);
        a.deflect(this);
    }
    
    public void takeCollisionDamage(ArenaActor a)
    {
        
    }
    
    public void deflect(ArenaActor a)
    {
        
        
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
    
    public void setExactLocation(Location location)
    {
        this.location = location;
    }
    
    public void setExactLocation(double x, double y)
    {
        this.location.setX(x);
        this.location.setY(y);
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
    
    /**
     * Has this ArenaActor take a given amount of damage
     * 
     * @param damage    the damage to be dealt
     */
    abstract public void takeDamage(double damage);
    
    /**
     * Returns the health of this ArenaActor
     * 
     * @return  the health of this ArenaActor
     */
    abstract public double getHealth();
}
