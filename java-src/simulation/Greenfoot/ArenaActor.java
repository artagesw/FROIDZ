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
    private int velocity;

    /**
     * Constructor: set velocity to 0
     */
    public ArenaActor()
    {
        this.velocity = 0;
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
    
    public int getVelocity()
    {
        return this.velocity;
    }
}
