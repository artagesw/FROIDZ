 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
/**
 * ArenaActors are circles that interact with their surroundings.
 *  examples include...
 *      Robots
 *      Bullets
 *      Etc
 * 
 * @author Brendan Redmond and Haley B-E and Jacob Weiss
 * @version 0.3.0
 */
abstract public class ArenaActor extends Actor
{
    // the number of time units elapsed for each actor's act method in MILISECONDS
    public static final int ACT_TIME = 10;

    // the exact physical state of this.
    protected Physics state;
    
    public List<ArenaActor> hasCollided = new LinkedList();
    
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
    
    public ArenaActor(double mass, double x, double y, double radius)
    {
        super();
        this.state = new Physics(mass, radius, x, y);
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
    protected void resolveCollisions()
    {
        List<ArenaActor> c = getIntersectingCollidables();
        c.removeAll(this.hasCollided);
        
        if (c.size() != 0)
        {
            this.recursiveRevert();
            ArenaActor other = (ArenaActor)c.get(0);
            this.getState().collide(other.getState());
            this.processCollision(other);
            other.hasCollided.add(this);
        }
        this.hasCollided.clear();
        return;  
    }
    
    public void processCollision(ArenaActor other)
    {
        
    }
    
    public List<ArenaActor> getIntersectingCollidables()
    {
        List<ArenaActor> l = (List<ArenaActor>)this.getIntersectingObjects(ArenaActor.class);
        Iterator<ArenaActor> iterator = l.iterator();
        while (iterator.hasNext())
        {
            if (!iterator.next().getState().intersects(this.getState()))
            {
                iterator.remove();
            }
        }
        return l;
    }
    
    /**
     * recursiveRevert(Actor... ignore)
     * 
     * Move this actor so that it is no longer in contact with any other actor.
     * POSTCONDITION: this.getIntersectingObjects(Actor) == 0
     * 
     * @param   Actor... ignore     any actors that should be ignored can be passed in to
     *                              increase performance speed
     */
    public void recursiveRevert(Actor... ignore)
    {      
        List<Obstacle> obstacles = (List<Obstacle>)this.getIntersectingObjects(Obstacle.class);
        obstacles.remove(ignore);
        if (obstacles.size() != 0)
        {
            for (Obstacle obstacle : obstacles)
            {
                Vector n = obstacle.intersects(this);
                if (n != null)
                {
                    this.getState().getDisplacement().add(n.scale(-1));
                    this.update();
                }
            }
        }
        List<ArenaActor> collidables = this.getIntersectingCollidables();
        collidables.remove(ignore);
        for (Object o : collidables)
        {
            ArenaActor actor = (ArenaActor)o;
            Vector D = actor.getState().getDisplacement().addCopy(this.getState().getDisplacement().scaleCopy(-1));
            D.unitVector().scale(this.getState().getRadius() + actor.getState().getRadius() + 1);
            actor.getState().setDisplacement(this.getState().getDisplacement().addCopy(D));
            actor.update();
            actor.recursiveRevert();
        }
        assert(this.getIntersectingObjects(ArenaActor.class).size() == 0);
    }
    
    public void takeDamage(int damage)
    {
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
