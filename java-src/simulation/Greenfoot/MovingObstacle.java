

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.geom.Path2D;
import java.util.ArrayList;
/**
 * MovingObstacles are obstacles that can rotate and move.
 * 
 * Forces do not act on moving obstacles. They supply energy to the system and
 * therefor break energy conservation.
 * 
 * @author Jacob Weiss
 * @version 0.2.0
 */
public class MovingObstacle extends Obstacle
{
    private double rotationalVelocity = 0;
    private double orientation = 0;
    private ArrayList<Vector> path = new ArrayList();
    private int currentSegment = 0;
    private double speed = 0;
    private Vector previousDisplacement = new Vector();
    private Vector displacement = new Vector();
    
    public MovingObstacle(int[]... args)
    {
        super(args);
    }
    
    public void setPath(Vector... segments)
    {
        this.previousDisplacement.copy(this.displacement);
        for (Vector v : segments)
        {
            this.path.add(v);
        }
    }
    public void setRotationalVelocity(double degPerSec)
    {
        this.rotationalVelocity = degPerSec;
    }
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
    
    public void setRotation(int rotation)
    {
        this.orientation = rotation;
        super.setRotation(rotation);
    }
    
    public void act()
    {
        this.orientation += ArenaActor.ACT_TIME/1000.0 * this.rotationalVelocity;
        super.setRotation((int)this.orientation);
        
        double dx = this.displacement.getI() - this.previousDisplacement.getI();
        double dy = this.displacement.getJ() - this.previousDisplacement.getJ();
        if (Math.sqrt(dx * dx + dy * dy) > this.path.get(this.currentSegment).magnitude())
        {
            this.currentSegment = (this.currentSegment + 1) % this.path.size();
            this.previousDisplacement.copy(this.displacement);
        }
        this.displacement.add(this.path.get(this.currentSegment).unitVectorCopy().scale(this.speed * ArenaActor.ACT_TIME / 1000.0));
        this.updateLocation();
        
        super.act();
    }
    
    public void setLocation(double x, double y)
    {
        super.setLocation((int)x, (int)y);
        this.displacement.setI(x);
        this.displacement.setJ(y);
    }
    public void setLocation(int x, int y)
    {
        this.setLocation((double)x, (double)y);
    }
    
    private void updateLocation()
    {
        super.setLocation((int)this.displacement.getI(), (int)this.displacement.getJ());
    }
    
    public Vector extend(Vector normal, Physics state)
    {
        Vector w = new Vector();
        w.copy(normal);
        w.unitVector().scale(-1 * state.getRadius());
        double dx = (this.getX() - state.getDisplacement().getI());
        double dy = (this.getY() - state.getDisplacement().getJ());
        w.add(dx, dy);
        double radius = w.magnitude();
        w.normal().unitVector().scale(this.rotationalVelocity * Math.PI / 180 * radius * -1).componentInDirection(normal);
        
        Vector v = new Vector();
        v.copy(this.path.get(this.currentSegment)).unitVector().scale(this.speed);
        v.componentInDirection(normal);
        
        return w.add(v).scale(.5);
    }
}
