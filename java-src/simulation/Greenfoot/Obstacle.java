import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * An Obstacle for the Arena.
 * 
 * @author Brendan Redmond
 * @version 0.1.0
 */
public class Obstacle extends ArenaActor
{
    //the density of this Obstacle in kg per pixel
    private static double DENSITY = 1.0;

    //the current health of this Obstacle
    private double health;
    
    public Obstacle(int width, int height, double health)
    {
        assert(health > 0);
         
        this.health = health;
        this.setImage(new GreenfootImage(width, height));
        this.getImage().setColor(Color.BLACK);
        this.getImage().fillRect(0, 0, width, height);    
    }
     
    public double getHealth()
    {
       return this.health;
    } 
    
    public double getMass()
    {
        return this.getImage().getWidth() * this.getImage().getHeight() * DENSITY;
    }
    
    public void takeDamage(double damage)
    {
        this.health -= damage;
    }
}
