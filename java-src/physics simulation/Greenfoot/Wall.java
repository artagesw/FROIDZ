import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * Boundaries for the Arena, indestructable.
 * 
 * @author Brendan Redmond
 * @version 0.2.0
 */
public class Wall extends Actor
{
    public static final int THICKNESS = 30;

    private int length;
    
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
        // Get a list of all objects that are in contact with this wall.
        List<Collidable> collisions = this.getIntersectingObjects(Collidable.class);
        
        if (collisions.size() == 0)
        {
            return;
        }
        
        /*
        // Revert it to its previous location because its previous location was not in a wall.
        // This avoid double collisions and keeps the ball from going though the wall.
        for (Object o : this.getWorld().getObjects(Collidable.class))
        {
            ((Collidable)o).getState().revert();
            ((ArenaActor)o).update();
        }
        */
       
        // Loop through the collided objects
        for (Collidable c : collisions)
        {                       
            // Get the state of the object.
            Physics state = c.getState();
            
            ((ArenaActor)c).recursiveRevert();
            
            // Determine the tangent vector of the collision
            Vector tangent = new Vector(this.getRotation());
            
            // Determine the component of the velocity of the collided object that lies normal
            // to the tangent of the collision, but opposite the direction that the object currently
            // moves.
            Vector normalComp = state.getVelocity().componentInDirectionCopy(tangent.normal().scale(-1));
            
            // Multiply this state by 2 and add it to the velocity. This change only modifies the 
            // component of the velocity normal to the collision plane, causing the object to
            // "bounce" off the wall at the same angle that it collided at it will.
            state.getVelocity().add(normalComp.scale(-2));
            
            state.act();
        }
        
        /*
        // Prevent the bots from getting into a "revert" loop where they stick to a wall and 
        // stop moving.       
        for (Object o : this.getWorld().getObjects(Collidable.class))
        {
            ((Collidable)o).getState().act();
            ((ArenaActor)o).update();
        }
        */
    }   
}
