import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boundaries for the Arena, indestructable.
 * 
 * @author Brendan Redmond
 * @version 0.1.0
 */
public class Wall extends Obstacle
{
    public Wall(int width, int height)
    {
        super(width, height, Integer.MAX_VALUE);
    }
        

    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   
    
    public void takeDamage(double damage)
    {
    }
    
    public double getMass()
    {
        return 0.0;
    }
}
