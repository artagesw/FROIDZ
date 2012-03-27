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
    //the number of time units elapsed for each actor's act method
    public static final int ACT_TIME = 10;

    //the current speed of this ArenaActor in cells per unit time
    private double speed;
    private double rotation;
    //the exact location of this ArenaActor
    private Location location;

    /**
     * Constructor: set speed to 0
     */
    public ArenaActor()
    {
        this.speed = 0;
        this.location = null;
    }
    
    /**
     * Constructor: set speed, direction to given values
     * 
     * @param speed     the given speed
     * @param direction the given direction
     * @param angle     the given angle
     */
    public ArenaActor(double speed, double direction)
    {
        assert(speed >= 0);
        assert(Math.abs(direction) < 360);
        assert(Math.abs(direction) >= 0);
        
        this.speed = speed;
        this.setRotation(direction);
        this.location = new Location();
    }
    
    
    //wrapper methods that take doubles in order to utilize greenfoot's movement methods that require ints
    
    /**
     * Sets the ArenaActor's location to the nearest integer location to the double coordinates passed
     * @param x     double x-coordinate of new location
     * @param y     double y-coordinate of new location
     */
    public void setLocation(double x, double y)
    {
        super.setLocation((int) (x + .5), (int) (y + .5));
        
        this.location.setX(x);
        this.location.setY(y);
    }
    
    public void setLocation(Location location)
    {
        this.location = location;
    }

    
    /**
     * Sets the ArenaActor's direction to the nearest integer location to the double value passed
     * @param rotation     double value of new rotation 
     */
    public void setRotation(double rotation)
    {
        super.setRotation((int) (rotation + .5));
        this.rotation = rotation;
    }
    
    public double getExactRotation()
    {
        return this.rotation;
    }
    
    public double getExactX()
    {
        return this.location.getX();
    }
    
    public double getExactY()
    {
        return this.location.getY();
    }
    
    public Location getExactLocation()
    {
        return this.location;
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
    
    public Location getLocation()
    {
        return this.location;
    }
    
    public void turn(int angle)
    {
        super.turn(angle);
        this.rotation += angle;
        this.rotation %= 360;
    }
    
    //methods dealing with movement    
    
    
    public void act()
    {
        this.move(2);
        //this.move(this.speed * ACT_TIME);
    }

    public void move(int distance)
    {
        while (distance > 0)
        {
            this.moveOne();
            this.resolveCollisions();
            distance--;
        }
        
    }
    
    public void moveOne()
    {
        double x = this.getExactX();
        double y = this.getExactY();
        
        this.setLocation((x + (Math.cos(Math.toRadians(this.getExactRotation())))), 
                         (y + (Math.sin(Math.toRadians(this.getExactRotation())))));  
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
        assert(Arena.xIsInBoundaries(x));
        assert(Arena.yIsInBoundaries(y));
        
        double angle;
        
        if (x > this.getExactX())
        {
            angle = Math.toDegrees(Math.atan((y - this.getY()) / (x - this.getX())));
        }
        else if (x < this.getExactX())
        {
            angle = Math.toDegrees(Math.atan((y - this.getY()) / (x - this.getX()))) + 180;
        }
        else
        {
            if (y > this.getExactY())
            {
                return 90;
            }
            else if (y < this.getExactY())
            {
                return 270;
            }
            else
            {
                return this.getExactRotation();
            }
        }
        
        assert(angle >= 0);
        assert(angle < 360);
        
        return angle;
    }
    
    /**
     * Returns the angle this ArenaActor must have to face a given Actor
     */
    public double getAngleTowards(ArenaActor a)
    {
        return this.getAngleTowards(a.getExactX(), a.getExactY());
    }

    
    /**
     * Resolves collisions between this ArenaActor and any intersecting ArenaActors
     */
    private void resolveCollisions()
    {
        List<ArenaActor> actors = this.getIntersectingObjects(ArenaActor.class);
        List<Wall> walls = this.getIntersectingObjects(Wall.class);
        
        
        for (Wall w : walls)
        {
            this.deflect(w);
        }
        
        for (ArenaActor a : actors)
        {
            this.collideWith(a);
            this.deflect(a);
        }
    }
    /*
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
        
    }*/
    
    /**
     * Deals collision damage to the given intersecting ArenaActor and changes the movement of
     *  this ArenaActor as a result of the collision
     *  
     * @param a     the intersecting ArenaActor
     */
    private void collideWith(ArenaActor a)
    {
        this.takeCollisionDamage(a);
        a.takeCollisionDamage(this);
    }
    
    public void takeCollisionDamage(ArenaActor a)
    {
        assert(a != null);
    }
    
    public void deflect(ArenaActor a)
    {
        assert(a != null);
        
        this.setRotation(2 * this.getAngleTowards(a) - this.getExactRotation());
    }
    
    public void deflect(Wall w)
    {
       this.setRotation(2 * w.getRotation() - this.getExactRotation());

       /** int angle = this.getRotation();
        int newAngle = 30;
        int quad = angle / 90;

        
        if ((angle == 0) || (angle == 180))
        {
            newAngle = 180 - angle;
        }
        else if (angle == 270)
        {
            newAngle = 90;
        }
        else if (angle == 90)
        {
            newAngle = 270;
        }
        else
        {
                    
            if (w.getRotation() == 0)
            {
                if ((quad == 0) || (quad == 2))
                {
                    newAngle = 180 - angle;
                }
                else
                {
                    newAngle = angle + 90;
                }
            }
            else
            {
                newAngle = 360 - angle;
            }
        }
        
        this.setRotation(newAngle);*/
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
