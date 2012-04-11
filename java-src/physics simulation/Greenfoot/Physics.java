import greenfoot.*;

/**
 * The Physics class represents 1 object in 2 dimentional space.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class Physics
{
    private double mass; // in kilograms
    private double orientation; // in degrees
    private Vector displacement; // in meters
    private Vector velocity; // in meters / second
    private Vector acceleration; // in meters / second^2
    private Vector force; // in newtons
    private double radius; // in meters
    
    private CircularStack previousDisplacement = new CircularStack(3); // Used after collisions.
    
    public Physics(double mass, double radius, double x, double y)
    {
        this.mass = mass;
        this.displacement = new Vector(x, y);
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.force = new Vector();
        this.radius = radius;
    }
    
    public Physics()
    {
        this(1, 25, 0, 0);
    }
    
    /**
     * collide()
     * 
     * Collide two physics objects. Adapted from http://en.wikipedia.org/wiki/Elastic_collision
     * 
     * This method modifies p1 and p2 to reflect their state post-collision
     * 
     * @param   Physics     the object to collide this with
     */
    public void collide(Physics p2)
    {
            Physics p1 = this;
            
            Vector Dn = p1.displacement.add(p2.displacement.scale(-1));
 
            // The distance between the balls
            double delta = Dn.magnitude();
     
            // The normal vector of the collision plane
            Dn = Dn.unitVector();
     
            // The tangential vector of the collision plane
            Vector Dt = Dn.normal();
            
            //the masses of the two balls
            double m1 = p1.mass;
            double m2 = p1.mass;
            double M = m1 + m2;
            
            // the velocity vectors of the balls before the collision
            Vector v1 = p1.velocity;
            Vector v2 = p2.velocity;
     
            // split the velocity vector of the first ball into a normal and a
            // tangential component in respect of the collision plane
            Vector v1n = v1.componentInDirection(Dn);
            Vector v1t = v1.componentInDirection(Dt);
            
            // split the velocity vector of the second ball into a normal and a
            // tangential component in respect of the collision plane
            Vector v2n = v2.componentInDirection(Dn);
            Vector v2t = v2.componentInDirection(Dt);
     
            // calculate new velocity vectors of the balls, the tangential component
            // stays the same, the normal component changes analog to the 1-Dimensional case            
            p1.velocity = v1t.add(v1n.scale(m1-m2).add(v2n.scale(2*m2)).scale(1/(m1+m2)));
            p2.velocity = v2t.add(v2n.scale(m2-m1).add(v1n.scale(2*m1)).scale(1/(m1+m2)));
    }
    
    /**
     * act(int time)
     * 
     * Update this object to reflect the passing of time.
     * 
     * @param   int    time interval in miliseconds (seconds / 1000)
     */
    public void act()
    {
        this.acceleration = this.force.scale(1 / this.mass);
        this.velocity = this.velocity.add(this.acceleration.scale(ArenaActor.ACT_TIME / 1000.0));
        this.displacement = this.displacement.add(this.velocity.scale(ArenaActor.ACT_TIME / 1000.0));
        this.previousDisplacement.push(this.displacement);
    }
    
    /**
     * revert()
     * 
     * Revert this to its previous location. This is usefull for avoiding double collisions.
     */
    public boolean revert()
    {
        Vector v = this.previousDisplacement.pop();
        if (v == null)
        {
            return false;
        }
        this.displacement.copy(v);
        return true;
    }
    
    /**
     * applyForce(Vector v)
     * 
     * Applies the given force to this objects state.
     * 
     * @param   Vector  the force to apply
     */
    public void applyForce(Vector v)
    {
        this.force = v;
    }
    
    /**
     * setLocation(double x, double y)
     * 
     * Sets the displacement of this to a given point.
     * 
     * @param   double  the x coordinate
     * @param   double  the y coordinate
     */
    public void setLocation(double x, double y)
    {
        this.displacement = new Vector(x, y);
    }
    
    // Public getters and setters
    public double getMass()
    {
        return this.mass;
    }
    public void setDisplacement(Vector d)
    {
        this.displacement = d;
    }
    public Vector getDisplacement()
    {
        return this.displacement;
    }
    public void setVelocity(Vector v)
    {
        this.velocity = v;
    }
    public Vector getVelocity()
    {
        return this.velocity;
    }
    public Vector getAcceleration()
    {
        return this.acceleration;
    }
    public Vector getForce()
    {
        return this.force;
    }
    public double getOrientation()
    {
        return this.orientation;
    }
    public void setOrientation(double angle)
    {
        this.orientation = angle;
    }
    
    // toString method provided for debugging.
    public String toString()
    {
        String s = "m = " + this.mass +
                   ", d = " + this.displacement + 
                   ", v = " + this.velocity +
                   ", a = " + this.acceleration +
                   ", f = " + this.force;
        return s;
    }
}
