 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Boundaries for the Arena, indestructable.
 * 
 * @author Brendan Redmond
 * @version 0.2.0
 */
public class Wall extends Actor implements Collidable
{
    public static final int THICKNESS = 10;

    public Wall(int width, int height)
    {
        super();
        this.setImage(new GreenfootImage(width, height));
        this.getImage().setColor(Color.BLACK);
        this.getImage().fillRect(0, 0, width, height); 
    }
        

    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   
    
    public int getDeflectionAngle()
    {
        return this.getRotation();
    }
    
    public void setDeflectionRotation(int angle)
    {
    }
    public void smallMove(double distance)
    {
        
    }
}
