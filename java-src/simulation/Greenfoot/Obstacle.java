package simulation.Greenfoot;

 

 

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * An Obstacle for the Arena.
 * 
 * @author Brendan Redmond
 * @version 0.2.0
 */
public class Obstacle extends ArenaActor
{
    //the density of this Obstacle in kg per pixel
    private static double DENSITY = 1.0;

    //the current health of this Obstacle
    private double health;
    
    /**
     * Constructor: creates an Obstacle of given width, height, and health
     * 
     * @param width     the given width
     * @param height    the given height
     * @param health    the given health
     */
    public Obstacle(int width, int height, double health)
    {
        super(); 
        assert(health > 0);
        
        this.health = health;
        this.setImage(new GreenfootImage(width, height));
        this.getImage().setColor(Color.BLACK);
        this.getImage().fillRect(0, 0, width, height);    
    }
     
    /**
     * Returns the current health of this Obstacle
     * 
     * @return  the current health of this Obstacle
     */
    public double getHealth()
    {
       return this.health;
    } 
    
    /**
     * Returns the mass of this Obstacle
     * 
     * @return  the mass of this Obstacle
     */
    public double getMass()
    {
        return this.getImage().getWidth() * this.getImage().getHeight() * DENSITY;
    }
    
    /**
     * Deals this Obstacle a given amount of damage
     * 
     * @param damage    the damage to be dealt
     */
    public void takeDamage(double damage)
    {
        this.health -= damage;
    }
    
    public void act()
    {
    }
}
