import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

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

    /**
     * Constructor: set speed to 0
     */
    public ArenaActor()
    {
        this.speed = 0;
        this.acceleration = 0;
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
    }
    
    
    //wrapper methods that take doubles in order to utilize greenfoot's movement methods that require ints
    
    /**
     * Sets the ArenaActor's location to the nearest integer location to the double coordinates passed
     * @param x     double x-coordinate of new location
     * @param y     double y-coordinate of new location
     */
    public void setLocation(double x, double y)
    {
        super.setLocation((int)Math.round(x), (int)Math.round(y));
    }

    
    /**
     * Sets the ArenaActor's direction to the nearest integer location to the double value passed
     * @param rotation     double value of new rotation 
     */
    public void setRotation(double rotation)
    {
        this.setRotation((int)Math.round(rotation));
    }
    

    
    //other public getter/setter methods and variable modifiers

    
    public double getSpeed()
    {
        return this.speed;
    }
    
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
    
    /**
     * Changes the speed of this ArenaActor by a given amount
     * 
     * @param change  the amount by which the speed of this ArenaActor will change (negative number decreases speed, positive increases)
     */
    public void changeSpeed(double change)
    {
        this.speed += change;
    }
    
    
    
    //methods dealing with movement    
    
    
    public void act()
    {
        move(10);
    }

    public void move(int distance)
    {
        while (distance > 0)
        {
            super.move(1);
            this.resolveCollisions();
            distance--;
        }
    }
    
    
    
    /**
     * Moves the actor to a new location 
     */
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
    public double getAngleTowards(int x, int y)
    {
        assert(this.xIsInBoundaries(x));
        assert(this.yIsInBoundaries(y));
        
        double angle;
        
        if (x == this.getX())
        {
            angle = 0;
        }
        else
        {
            angle = Math.toDegrees(Math.atan((y - this.getY()) / (x - this.getX())));
        }
        
        //if the location is to the right, 180 must be added to the angle
        if (x - this.getX() > 0)
        {
            angle += 180;
        }
        
        assert(angle >= 0);
        assert(angle < 360);
        
        return angle;
    }
    
    /**
     * Returns the angle this ArenaActor must have to face a given Actor
     */
    public double getAngleTowards(Actor a)
    {
        return this.getAngleTowards(a.getX(), a.getY());
    }

    
    /**
     * Resolves collisions between this ArenaActor and any intersecting ArenaActors
     */
    private void resolveCollisionss()
    {
        List<ArenaActor> actors = this.getIntersectingObjects(ArenaActor.class);
        List<Wall> walls = this.getIntersectingObjects(Wall.class);
        
        
        for (ArenaActor a : actors)
        {
            this.collideWith(a);
        }
        for (Wall w : walls)
        {
            this.deflect(w);
        }
    }
    
    private void resolveCollisions()
    {
        int width = (this.getImage().getWidth() / 2);
        int height = (this.getImage().getHeight() / 2);
        
        List<Actor> intersecting = new ArrayList<Actor>();
        
        for (int i = (this.getX() - width); i <= (this.getX() + width); i++)
        {
            for (int j = (this.getY() - height); j <= (this.getY() + height); j++)
            {
                List<Actor> toAdd = this.getWorld().getObjectsAt(i, j, Actor.class);
                
                if (toAdd != null)
                {
                    for (Actor a : toAdd)
                    {
                        intersecting.add(a);
                    }
                }
            }
        }
        
        for (Actor a : intersecting)
        {
            if (a instanceof ArenaActor)
            {
                this.collideWith((ArenaActor)a);
            }
            else if (a instanceof Wall)
            {
                this.deflect((Wall)a);
            }
        }
        
    }
    
    /**
     * Deals collision damage to the given intersecting ArenaActor and changes the movement of
     *  this ArenaActor as a result of the collision
     *  
     * @param a     the intersecting ArenaActor
     */
    private void collideWith(ArenaActor a)
    {
        a.takeCollisionDamage(this);
        
        this.deflect(a);
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
    
    public void deflect(Wall w)
    {
        int angle = this.getRotation();
        int newAngle;
        
        if ((angle < 90) && (angle > 0))
        {
            newAngle = 360 - angle;
        }
        else if (((angle <= 180) && (angle > 90)) || (angle == 0))
        {
            newAngle = 180 - angle;
        }
        else if ((angle < 270) && (angle != 90))
        {
            newAngle = 90 + angle;
        }
        else if (angle == 90)
        {
            newAngle = 270;
        }
        else if (angle == 270)
        {
            newAngle = 90;
        }
        else
        {
            newAngle = 360 - angle;
        }
        
        this.setRotation(newAngle);
    }

    

    
    //abstract methods
    
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
    
    
    //Aids for assertions

    public boolean xIsInBoundaries(int x)
    {
        return ((x >= 0) && (x <= Arena.WIDTH));
    }
   
    public boolean yIsInBoundaries(int y)
    {
        return ((y >= 0) && (y <= Arena.WIDTH));
    }

    
    public boolean isInBoundaries (int x, int y)
    {
        return (this.xIsInBoundaries(x) && this.yIsInBoundaries(y));
    }
}
