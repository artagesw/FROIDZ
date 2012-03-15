import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A genereric projectile
 * 
 * @author Brendan Redmond
 * @version 0.0.2
 */
public class Projectile extends ArenaActor
{
    //the mass of this projectile in kilograms
    private double mass;

    /**
     * Constructor: calls super, sets the velocity and mass to given values
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
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
