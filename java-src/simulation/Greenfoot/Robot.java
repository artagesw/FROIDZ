import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A stub class.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot extends ArenaActor
{
    /**
     * Act - do whatever the Robot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public double getMass()
    {
        return 0.0;
    }
    
    public boolean isInContact()
    {
        return (this.getOneIntersectingObject(ArenaActor.class) != null);
    }
}
