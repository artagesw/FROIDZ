import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.geom.Path2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.Arrays;
/**
 * A Wall represents a solid polygon and deals with collisions.
 * 
 * Walls are stationary and indestructable.
 * 
 * @author Jacob Weiss
 * @version 0.3.0
 */
public class Wall extends Actor
{
    // The standard width of a wall. (Should be subclassed)
    public static final int THICKNESS = 30;
    
    // The path that represents the outline of this.
    private Path2D outline = new Path2D.Double();
    
    // An array of the vertexes of this for faster collision detection.
    private double[][] points;
    
    // Remember the previous translation and rotation so that it can be undone
    private AffineTransform translateHistory = new AffineTransform();
    private AffineTransform rotateHistory = new AffineTransform();
    
    public Wall(int[]... args)
    {
        this.outline.moveTo(args[0][0], args[0][1]);
        for (int i = 1; i < args.length; i++)
        {
            this.outline.lineTo(args[i][0], args[i][1]);
        }
        this.outline.lineTo(args[0][0], args[0][1]);
        this.outline.closePath();
        
        this.points = new double[args.length + 1][2];
        
        GreenfootImage pic = new GreenfootImage((int)this.outline.getBounds().getWidth(), (int)this.outline.getBounds().getHeight());
        pic.setColor(Color.BLACK);
        pic.fillShape(this.outline);
        this.setImage(pic);
    }
    
    /**
     * updateOutline()
     * 
     * Updates this.outline to reflect a change in position or rotation.
     */
    private void updateOutline()
    {
        try
        {
            this.translateHistory.invert();
            this.rotateHistory.invert();
        }
        catch (java.awt.geom.NoninvertibleTransformException e)
        {
            System.out.println("you broked it");
        }
        this.outline.transform(this.rotateHistory);
        this.outline.transform(this.translateHistory);
        
        this.translateHistory = AffineTransform.getTranslateInstance(this.getX() - (int)this.outline.getBounds().getWidth() / 2, this.getY() - (int)this.outline.getBounds().getHeight() / 2);
        this.rotateHistory = AffineTransform.getRotateInstance(this.getRotation() * Math.PI / 180, this.getX(), this.getY());
        
        this.outline.transform(this.translateHistory);
        this.outline.transform(this.rotateHistory);
    }
        
    public void setLocation(int x, int y)
    {
        super.setLocation(x, y);
        this.updateOutline();
        this.updatePoints();
    }
    
    public void setRotation(int rotation)
    {
        super.setRotation(rotation);
        this.updateOutline();
        this.updatePoints();
    }
    
    /**
     * updatePoints()
     * 
     * Update this.points to reflect the current position of this.
     */
    private void updatePoints()
    {
        PathIterator iterator = this.outline.getPathIterator(null, 1);
        
        for (int i = 0; i < this.points.length; i++)
        {   
            double[] coords = new double[2];
            if (iterator.isDone() || iterator.currentSegment(coords) == PathIterator.SEG_CLOSE)
            {
                return;
            }
            this.points[i] = coords;
            iterator.next();
        }
    }
    
    /**
     * printCoords()
     * 
     * Prints the coordinates of the vertexes of this.
     */
    public void printCoords()
    {
        for (double[] i : this.points)
        {
            System.out.println(Arrays.toString(i));
        }
    }
    
    /**
     * act()
     * 
     * Each act test for collidables that are intersecting with this and 
     * responds accordingly.
     */
    public void act()
    {
        List<Collidable> collisions = (List<Collidable>)this.getIntersectingObjects(Collidable.class);
            
        for (Collidable actor : collisions)
        {
            Vector normal;
            if ((normal = this.intersects(actor)) != null)
            {
                Physics state = actor.getState();
                state.revert();
                ((ArenaActor)actor).update();
                ((ArenaActor)actor).recursiveRevert();
                
                // Determine the component of the velocity of the collided object that lies normal
                // to the tangent of the collision, but opposite the direction that the object currently
                // moves.
                Vector normalComp = state.getVelocity().componentInDirectionCopy(normal);
                
                // Multiply this state by 2 and add it to the velocity. This change only modifies the 
                // component of the velocity normal to the collision plane, causing the object to
                // "bounce" off the wall at the same angle that it collided at it will.
                state.getVelocity().add(normalComp.scale(-2));
                
                state.act();
            }
        }
    }
        
    /**
     * intersects()
     * 
     * Determines if the given collidable intersects with this wall.
     * 
     * @param   Collidable  the collidable to test
     * @return  Vector      the normal vector to the collision plane
     *          null        if the collidable does not intersect this wall
     */
    public Vector intersects(Collidable actor)
    {
        Physics state = actor.getState();
        Vector displacement = state.getDisplacement();
        for (int i = 0; i < this.points.length - 1; i++)
        {
            double x0 = this.points[i][0];
            double y0 = this.points[i][1];
            double x1 = this.points[i+1][0];
            double y1 = this.points[i+1][1];
            
            Vector side = new Vector(x0 - x1, y0 - y1);
    
            Vector v1 = new Vector(displacement.getI() - x0, displacement.getJ() - y0);
            Vector v2 = new Vector(displacement.getI() - x1, displacement.getJ() - y1);
            double cosAngleA = side.scale(-1).dot(v1) / (v1.magnitude() * side.magnitude());
            double cosAngleB = side.scale(-1).dot(v2) / (v2.magnitude() * side.magnitude());
            
            if (cosAngleA <= 1 && cosAngleA >= 0 && cosAngleB <= 1 && cosAngleB >= 0)
            {
                double perpDistance;
                if (x0 - x1 == 0)
                {
                    perpDistance = Math.abs(x0 - displacement.getI());
                }
                else
                {
                    double slope = (y0 - y1) / (x0 - x1);
                    double c = -1 * (y0 - slope * x0);
                    double a = -1 * slope;
                    double b = 1;
                    perpDistance = Math.abs(a * displacement.getI() + b * displacement.getJ() + c) / Math.sqrt(a * a + b * b); 
                }
                if (perpDistance < state.getRadius())
                {                        
                    // Determine the tangent vector of the collision
                    Vector tangent = side;
                    
                    // Determine the component of the velocity of the collided object that lies normal
                    // to the tangent of the collision, but opposite the direction that the object currently
                    // moves.
                    return tangent.normal().scale(-1);
                }    
            }
        }
        for (int i = 0; i < this.points.length - 1; i++)
        {
            double x  = this.points[i][0];
            double y  = this.points[i][1];
            if (Math.sqrt((x - displacement.getI()) * (x - displacement.getI()) + (y - displacement.getJ()) * (y - displacement.getJ())) < state.getRadius())
            {                        
                return new Vector(x - displacement.getI(), y - displacement.getJ());                                              
            }
        }
        return null;
    }
}
