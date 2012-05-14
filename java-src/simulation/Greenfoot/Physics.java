 

import greenfoot.*;

/**
 * The Physics class represents 1 object in 2 dimentional space.
 * 
 * @author Jacob Weiss
 * @version 0.3.0
 */
public class Physics
{
    private double mass; // in kilograms
    
    private double rotationalVelocity; // in degrees / sec
    private double orientation; // in degrees
    
    private Vector displacement; // in pixles (1/10 ths of a meter)
    private Vector velocity; // in pixles (1/10 ths of a meter) / second
    private Vector acceleration; // in pixles (1/10 ths of a meter) / second^2
    private Vector force; // in newtons
    private double radius; // in pixles (1/10 ths of a meter)
    
    private boolean hasFriction = true;
    
    public Physics(double mass, double radius, double x, double y)
    {
        this.mass = mass;
        this.rotationalVelocity = 0;
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
    
    public double distanceFrom(Physics p2)
    {
        return this.displacement.addCopy(p2.displacement.scaleCopy(-1)).magnitude();
    }
    
    public boolean intersects(Physics p2)
    {
        return (this.radius + p2.radius) > this.distanceFrom(p2);
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
            
            Vector Dn = p1.displacement.addCopy(p2.displacement.scaleCopy(-1));
 
            // The distance between the balls
            double delta = Dn.magnitude();
     
            // The tangential vector of the collision plane
            Vector Dt = Dn.normalCopy();
            
            //the masses of the two balls
            double m1 = p1.mass;
            double m2 = p1.mass;
            double M = m1 + m2;
            
            // the velocity vectors of the balls before the collision
            Vector v1 = p1.velocity;
            Vector v2 = p2.velocity;
            
            // split the velocity vector of the first ball into a normal and a
            // tangential component in respect of the collision plane
            Vector v1n = v1.componentInDirectionCopy(Dn);
            v1.componentInDirection(Dt);
            
            // split the velocity vector of the second ball into a normal and a
            // tangential component in respect of the collision plane
            Vector v2n = v2.componentInDirectionCopy(Dn);
            v2.componentInDirection(Dt);
     
            // calculate new velocity vectors of the balls, the tangential component
            // stays the same, the normal component changes analog to the 1-Dimensional case            
            v1.add(v1n.scaleCopy(m1-m2).add(v2n.scaleCopy(2*m2)).scale(1/(m1+m2)));
            v2.add(v2n.scale(m2-m1).add(v1n.scale(2*m1)).scale(1/(m1+m2)));
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
        this.orientation = (this.rotationalVelocity * (ArenaActor.ACT_TIME / 1000.0) + this.orientation);
        if (hasFriction)
        {
            this.force.add(this.calculateFriction());
        }
        this.acceleration = this.force.scaleCopy(1 / this.mass);
        this.velocity.add(this.acceleration.scaleCopy(ArenaActor.ACT_TIME / 1000.0));
        this.displacement.add(this.velocity.scaleCopy(ArenaActor.ACT_TIME / 1000.0));
    }
    
    private Vector calculateFriction()
    {
        if (this.velocity.magnitude() == 0)
        {
            return new Vector();
        }
        Vector F = new Vector();
        F.copy(this.velocity).unitVector().scale(-1 * this.mass);
        return F;
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
    public void setRotationalVelocity(double d)
    {
        this.rotationalVelocity = d;
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
    public double getRadius()
    {
        return this.radius;
    }
    public void setFriction(boolean friction)
    {
        this.hasFriction = friction;
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
