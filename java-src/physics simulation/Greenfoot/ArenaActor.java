import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Common elements of actors that act in the Arena.
 * 
 * 
 * @author Brendan Redmond and Haley B-E
 * @version 0.2.0
 */
abstract public class ArenaActor extends Actor implements Collidable
{
    // the number of time units elapsed for each actor's act method in MILISECONDS
    public static final int ACT_TIME = 5;

    // the exact physical state of this.
    protected Physics state;
    
    public List<Collidable> hasCollided = new LinkedList();
    
    /**
     * Constructor: set speed, rotation to 0
     */
    public ArenaActor(Location l)
    {
        super();
        this.state = new Physics(5, 25, l.getX(), l.getY());
    }
    
    public ArenaActor(double mass, Location l, double radius)
    {
        super();
        this.state = new Physics(mass, radius, l.getX(), l.getY());
    }
    
    public ArenaActor()
    {
        super();
        this.state = new Physics();
    }
    
    //other public getter/setter methods and variable modifiers    
    
    public void act()
    {
        this.state.act();
        this.resolveCollisions();
        this.update();
    }
    
    public void update()
    {        
        super.setLocation((int)this.state.getDisplacement().getI(), (int)this.state.getDisplacement().getJ());
        super.setRotation((int)this.state.getOrientation());
    }
    
    /**
     * Resolves collisions between this ArenaActor and any intersecting ArenaActors
     */
    private void resolveCollisions()
    {
        List<Collidable> c = this.getIntersectingObjects(Collidable.class);
        c.removeAll(this.hasCollided);
        
        if (c.size() != 0)
        {
            for (Object o : this.getWorld().getObjects(Collidable.class))
            {
                ((Collidable)o).getState().revert();
                ((ArenaActor)o).update();
            }
        }
        else
        {
            return;
        }          
        
        for (Collidable o : c)
        {
            this.getState().collide(o.getState());
            this.update();
            ((ArenaActor)o).update();
            ((ArenaActor)o).hasCollided.add(this);
        }
        
        for (Object o : this.getWorld().getObjects(Collidable.class))
        {
            ((Collidable)o).getState().act();
            ((ArenaActor)o).update();
        }
        
        this.hasCollided.clear();
    }
    
    public boolean intersects(Actor a)
    {
        return super.intersects(a);
    }
    
    public Physics getState()
    {
        return this.state;
    }
    
    public void setLocation(Location l)
    {
        this.state.setLocation(l.getX(), l.getY());
        super.setLocation((int)l.getX(), (int)l.getY());
    }
    
    public void setLocation(double x, double y)
    {
        this.state.setLocation(x, y);
        super.setLocation((int)x, (int)y);
    }
    
    public void setRotation(double r)
    {
        this.state.setOrientation(r);
        super.setRotation((int)r);
    }
    
    public void setRotation(int r)
    {
        this.state.setOrientation(r);
        super.setRotation(r);
    }
    
    public Location getLocation()
    {
        return new Location(this.state.getDisplacement().getI(), this.state.getDisplacement().getJ());
    }
}
