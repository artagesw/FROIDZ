 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A genereric projectile
 * 
 * @author Brendan Redmond
 * @version 0.1.0
 */
public class Projectile extends ArenaActor
{
    //the mass of this projectile in kilograms
    private double mass;

    /**
     * Constructor: sets the velocity and mass to given values
     * 
     * @param speed     the magnitude of the velocity to be set
     * @param direction the direction of the velocity to be set
     * @param mass      the mass to be set
     */
    public Projectile(double speed, int direction, int mass)
    {
        super(speed, direction);
        
        this.mass = mass;
    }

    /**
     * Act - do whatever the Projectile wants to do.
     */
    public void act() 
    {
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
}
