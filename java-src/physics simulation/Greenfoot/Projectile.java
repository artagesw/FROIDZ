import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * A genereric projectile
 * 
 * @author Brendan Redmond
 * @version 0.2.0
 */
public class Projectile extends ArenaActor
{
    //the mass of this projectile in kilograms
    private double mass;
    
    private Vector velocity; //meters / second
    private Vector displacement;   //from (0, 0)
    
    //offset from robot image to projectile image for original placement
    public static final int BUFFER = 5;
    
    //standard size of projectiles
    private final int PROJECTILE_WIDTH = 10;
    private final int PROJECTILE_HEIGHT = 10;

    //stores whether it's the first turn in order to properly set displacement vector and avoid errors
    private boolean firstTurn;
    
    private Location location;
    
    /**
     * Constructor: sets the velocity and mass to given values
     * 
     * @param speed     the magnitude of the velocity to be set
     * @param direction the direction of the velocity to be set
     * @param mass      the mass to be set
     */
    public Projectile(double speed, double direction, double mass)
    {
        this.mass = mass;
        this.velocity = new Vector(Math.cos(direction) * speed, Math.sin(direction) * speed);
        this.displacement = new Vector();
        this.setRotation(direction);
        GreenfootImage image = new GreenfootImage(PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        image.setColor(Color.GREEN);
        image.fillOval(0, 0, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.setImage(image);
        this.firstTurn = true;
    }
    
    public Projectile(double speed, double mass, double radius, double x, double y)
    {
        super(mass, new Location(x, y), radius);
        
        GreenfootImage image = new GreenfootImage(PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        image.setColor(Color.GREEN);
        image.fillOval(0, 0, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.setImage(image);
        
        this.firstTurn = true;
        
    }

    /**
     * Act - do whatever the Projectile wants to do.
     */
    public void act() 
    {
        if (firstTurn)
        {
            this.displacement = new Vector(this.getX(), this.getY());
            this.location = new Location(this.getX(), this.getY());
            firstTurn = false;
        }
        
        this.displacement = this.displacement.add(this.velocity.scale(ArenaActor.ACT_TIME / 1000.0));
        this.location.setX(this.displacement.getI());
        this.location.setY(this.displacement.getJ());
    }    
    
    /**
     * Returns the current mass of this Projectile
     * 
     * @return  the current mass of this Projectile
     */
    public double getMass()
    {
        return this.mass;
    }
    
    /**
     * Returns the current health of this Projectile
     * 
     * @return  the current health of this Projectile
     */
    public double getHealth()
    {
        return 1;
    }
    
    /**
     * A projectile will disappear after it hits something
     */
    public void takeDamage(double damage)
    {
        ((Arena) this.getWorld()).removeObject(this);
    }
    
    public int getBuffer()
    {
        return BUFFER;
    }
}
