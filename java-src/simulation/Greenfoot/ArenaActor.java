import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Common elements of actors that act in the Arena.
 * 
 * 
 * @author Brendan Redmond and Haley B-E
 * @version 0.4.0
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
    
    //the current angle of the ArenaActor as a double as to prevent truncation in the ArenaActor's
    //actual rotation in Arena
    private double angle;

    /**
     * Constructor: set speed to 0
     */
    public ArenaActor()
    {
        this.speed = 0;
        this.acceleration = 0;
        this.angle = 0;
        this.location = new Location();
    }
    
    /**
     * Constructor: set speed, direction, and angle to given values
     * 
     * @param speed     the given speed
     * @param direction the given direction
     * @param angle     the given angle
     */
    public ArenaActor(double speed, int direction)
    {
        assert(speed >= 0);
        assert(Math.abs(direction) < 360);
        assert(Math.abs(direction) >= 0);
        
        this.speed = speed;
        this.setRotation(direction);
        this.angle = direction;
    }

    /**
     * Act - do whatever the ArenaActor wants to do. 
     * (To be overridden by subclasses)
     * 
     */
    public void act() 
    {       
        this.moveExactly(1.2);
        this.resolveCollisions();
    }   
    
    
    //exact locations/rotations - overrides of greenfoot methods
        //in each section, both methods are necessary because the first overrides the default and the second performs the function we actually need
    
    /**
     * Overrides greenfoot's setLocation method so it also changes the actor's stored exact location
     * @param x     integer x-coordinate of new location
     * @param y     integer y-coordinate of new location
     */
    public void setLocation(int x, int y)
    {
        this.setLocation((double)x, (double)y);
    }
    
    /**
     * Like greenfoot's setLocation method, but takes doubles instead of ints and stores double location in Location object
     * @param x     double x-coordinate of new location
     * @param y     double y-coordinate of new location
     * 
     */
    public void setLocation(double x, double y)
    {
        assert (this.xIsInBoundaries(x));
        assert (this.yIsInBoundaries(y));
        
        super.setLocation((int) Math.round(x), (int) Math.round(y));
        this.location.setX(x);
        this.location.setY(y);
    }

    
    /**
     * Overrides greenfoot's setRotation method so it also changes the actor's stored exact rotation
     * @param rotation     integer value of new rotation 
     */
    public void setRotation(int rotation)
    {
        this.setRotation((double)rotation);
    }
    
    /**
     * Like greenfoot's setRotation method, but takes doubles instead of ints and stores double rotation in a variable
     * @param rotation      double value of new rotation
     */
    public void setRotation(double rotation)
    {
        super.setRotation((int) Math.round(rotation));
        this.angle = rotation;
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
     * Returns the angle this ArenaActor must have to face two given coordinates
     * 
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @return      the angle this ArenaActor must have to face two given coordinates
     */
    public double getAngleTowards(double x, double y)
    {
        assert(this.xIsInBoundaries(x));
        assert(this.yIsInBoundaries(y));
        
        double angle = Math.toDegrees(Math.atan((y - this.location.getY()) / (x - this.location.getX())));
        
        //if the location is to the right, 180 must be added to the angle
        if (x - this.location.getX() > 0)
        {
            angle += 180;
        }
        
        assert(angle >= 0);
        assert(angle < 360);
        
        return angle;
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
        
        //temporary until Brendan physics-es
        if (actors.size() > 0)
        {
            this.setRotation(this.getRotation() + 180);
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
        assert(a != null);
        
        //double xDamage = Math.abs((this.kineticEnergyX() - a.kineticEnergyX()) * DAMAGE_PER_ENERGY);
        //double yDamage = Math.abs((this.kineticEnergyY() - a.kineticEnergyY()) * DAMAGE_PER_ENERGY);
        
        //this.takeDamage(xDamage + yDamage);
    }
    
    public void deflect(ArenaActor a)
    {
        assert(a != null);
        
        this.speed = 0;
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
    
    
    /**
     * 
     */
    public boolean xIsInBoundaries(double x)
    {
        return ((x >= 0) && (x <= Arena.WIDTH));
    }
    
    /**
     * 
     */
    public boolean yIsInBoundaries(double y)
    {
        return ((y >= 0) && (y <= Arena.WIDTH));
    }
    
}
