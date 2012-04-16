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
            Vector normal = this.intersects(actor);
            if (normal != null)
            {
                Physics state = actor.getState();
                
                // Correct for overlap between this and the actor
                state.getDisplacement().add(normal.scale(-1));
                
                // Because the intersection of this and the actor was already determined, there is
                // no need to do it again, so pass this to recursiveRevert so that it ignores this. 
                ((ArenaActor)actor).recursiveRevert(this);
                ((ArenaActor)actor).update();
                
                // Determine the component of the velocity of the collided object that lies normal
                // to the tangent of the collision, but opposite the direction that the object currently
                // moves.
                Vector normalComp = state.getVelocity().componentInDirectionCopy(normal).scale(-2);                
                
                // Multiply this state by 2 and add it to the velocity. This change only modifies the 
                // component of the velocity normal to the collision plane, causing the object to
                // "bounce" off the wall at the same angle that it collided at it will.
                state.getVelocity().add(normalComp);
                
                state.act();
            }
        }
    }
        
    /**
     * intersects()
     * 
     * Determines if the given collidable intersects with this wall.
     * 
     * To determine this, the method performs the following operations:
     *  1) Check to see if the actor is somewhere in front of one of the line segments of this, that is
     *     check to see if it is in between the two lines drawn perpendicular to each of the segments
     *     through its endpoints.
     *     2) If so, check to determine whether the actor is touching the given line segment.
     *        3) If both 1 and 2 are true, then the actor has collided with the current line segment of this.
     *  3) Check to see if the actor is touching one of the vertexes of this.
     *     4) If so, the actor has collided with a vertex of this
     * 
     * @param   Collidable  the collidable to test
     * @return  Vector      A vector whose...
     *                          direction is normal to the collision plane
     *                          magnitude is the distance the object must move in order to no longer be intersecting with this.
     *          null        if the collidable does not intersect this wall
     */
    public Vector intersects(Collidable actor)
    {
        // The state of the actor and its displacement are accessed enough to warrant direct references.
        Physics state = actor.getState();
        Vector displacement = state.getDisplacement();
        
        // Loop through each line segment that makes the outline of this.
        for (int i = 0; i < this.points.length - 1; i++)
        {
            // Get the two points that define the line segment.
            double x0 = this.points[i][0];
            double y0 = this.points[i][1];
            double x1 = this.points[i+1][0];
            double y1 = this.points[i+1][1];
            
            // Create a vector that connects one vertex to the next.
            Vector side = new Vector(x0 - x1, y0 - y1);
    
            // Create two more vectors that start at the actors current location and end at each vertex.
            Vector v1 = new Vector(displacement.getI() - x0, displacement.getJ() - y0);
            Vector v2 = new Vector(displacement.getI() - x1, displacement.getJ() - y1);
            
            // Use these three vectors to calculate the angle Robot-Vertex1-Vertex2 and Robot-Vertex2-Vertex1
            double cosAngleA = side.scale(-1).dot(v1) / (v1.magnitude() * side.magnitude());
            double cosAngleB = side.scale(-1).dot(v2) / (v2.magnitude() * side.magnitude());
            
            // If both angles are positive and accute, then it can be concluded that the actor is directly in front of this
            // segment of the wall because it is in between the two lines drawn perpendicular to the current side and through each of its end points.
            if (cosAngleA <= 1 && cosAngleA >= 0 && cosAngleB <= 1 && cosAngleB >= 0)
            {
                // Calculate the perpendicular distance between the actor and the current line segment.
                double perpDistance;
                if (x0 - x1 == 0) // Edge case to avoid division by 0
                {                    
                    perpDistance = Math.abs(x0 - displacement.getI());
                }
                else // Normal case.
                {
                    // Given ax + by + c = 0, the perpendicular distance from that line to point (x, y) is
                    // |ax + by + c| / sqrt(a^2 + b^2)
                    double slope = (y0 - y1) / (x0 - x1);
                    double c = -1 * (y0 - slope * x0);
                    double a = -1 * slope;
                    double b = 1;
                    perpDistance = Math.abs(a * displacement.getI() + b * displacement.getJ() + c) / Math.sqrt(a * a + b * b); 
                }
                // If this distance is less than the radius of the actor, than it is colliding with this segment.
                if (perpDistance < state.getRadius())
                {                        
                    // Determine the tangent vector of the collision
                    Vector tangent = side;
                    
                    // Determine the component of the velocity of the collided object that lies normal
                    // to the tangent of the collision, but opposite the direction that the object currently
                    // moves. Then, scale this vector so that it represents the current overlap between the
                    // actor and the current line segment so that the return result can be used to correct
                    // for overlap and avoid errors.
                    return tangent.normal().unitVector().scale(-1 * (state.getRadius() - perpDistance + 1));
                }    
            }
        }
        // If the given actor was not directly colliding with a side, then check the vertexes.
        for (int i = 0; i < this.points.length - 1; i++)
        {
            // Get the vertexes.
            double x  = this.points[i][0];
            double y  = this.points[i][1];
            double dx = x - displacement.getI();
            double dy = y - displacement.getJ();
            
            // Calculate the distance from the vertex to the actor.
            double d = Math.sqrt(dx * dx + dy * dy);
            
            // If the distance is less than the radius of the actor, than it is touching the actor.
            if (d < state.getRadius())
            {               
                // The normal vector to the collision is calculated to be the vector that starts
                // at the vertex and ends at the center of the actor.
                Vector normal = new Vector(dx, dy);
                // Scale the normal vector to represent the current overlap between the actor and
                // the wall so that it can be used to correct for the overlap.
                return normal.unitVector().scale(state.getRadius() - d + 1);                                              
            }
        }
        // If the actor was not found to intersect the wall anywhere, then return null.
        return null;
    }
}
