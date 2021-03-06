 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;

/**
 * A generic projectile - bounces off everything except Robots
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
    public static final int BUFFER = 10;
    
    //standard size of projectiles
    private final int PROJECTILE_WIDTH = 10;
    private final int PROJECTILE_HEIGHT = 10;
    
    private Location location;
    
    private final int DAMAGE_VALUE = 1;
    
    /**
     * Constructor: sets the velocity and mass to given values
     * 
     * @param speed     the magnitude of the velocity to be set
     * @param mass      the mass to be set
     * @param radius    the radius of the projectile in decimeters (pixles)
     * @param x         x-coordinate of the projectile's center
     * @param y         y-coordinate of the projectile's center
     */
    public Projectile(Vector velocity, double mass, double radius, double x, double y)
    {
        super(mass, x, y, radius);
        this.state.setVelocity(velocity);
        this.state.setFriction(false);
        
        GreenfootImage image = new GreenfootImage((int)(2 * radius), (int)(2 * radius));
        image.setColor(Color.GREEN);
        image.fillOval(0, 0, (int)(2 * radius), (int)(2 * radius));
        this.setImage(image);
        
    }
    
    
    /**
     * Resolves collisions between this Projectile and any intersecting ArenaActors
     */
    public void processCollision(ArenaActor other)
    {
        other.takeDamage(DAMAGE_VALUE);
        if (other instanceof RobotActor)
        {
            ((Arena) this.getWorld()).removeObject(this);
        }
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
