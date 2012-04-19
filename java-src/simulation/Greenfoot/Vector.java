package simulation.Greenfoot;

 

/**
 * A generic 2d vector. Immutable/
 * 
 * @author Jacob Weiss
 * @version 0.3.0
 */
public class Vector
{
    private double i; // The horizontal component of the vector
    private double j; // The vertical component of the vector
    
    // Public Constructors
    /**
     * Vector()
     * 
     * Constructs the zero vector.
     */
    public Vector()
    {
        this.i = 0;
        this.j = 0;
    }
    /**
     * Vector(double i, double j)
     * 
     * Constructs a vector with components i and j.
     */
    public Vector(double i, double j)
    {
        this.i = i;
        this.j = j;
    }
    /**
     * Vector(double direction)
     * 
     * Constructs a unit vector in a direction. Direction is in degrees.
     */
    public Vector(double direction)
    {
        this.i = Math.cos(direction * Math.PI / 180);
        this.j = Math.sin(direction * Math.PI / 180);
    }
    
    public Vector copy(Vector v)
    {
        this.i = v.i;
        this.j = v.j;
        return this;
    }
    
    /**
     * add(Vector v)
     * 
     * Add vector v to this.
     * 
     * @param   Vector  the vector to add to this
     * @return  Vector  this
     */
    public Vector addCopy(Vector v)
    {
        return new Vector(this.i + v.i, this.j + v.j);
    }
    public Vector add(Vector v)
    {
        this.i += v.i;
        this.j += v.j;
        return this;
    }
    public Vector add(double x, double y)
    {
        this.i += x;
        this.j += y;
        return this;
    }
    /**
     * magnitude()
     * 
     * Calculate the magnitude of this vector.
     * 
     * @return  double  the magnitude of this
     */
    public double magnitude()
    {
        return Math.sqrt((this.i * this.i) + (this.j * this.j));
    }
    
    public double direction()
    {
        return Math.atan2(this.j, this.i);
    }
    
    /**
     * dot(Vector v)
     * 
     * Calculates the dot product of this and vector v.
     * 
     * @param   Vector  the vector to dot this with
     * @return  double  the result
     */
    public double dot(Vector v)
    {
        return this.i * v.i + this.j * v.j;
    }
    
    /**
     * scale(double scaleFactor)
     * 
     * Perform a scaler multiplication of this vector.
     * 
     * @param   double  the scale factor
     * @return  Vector  the result
     */
    public Vector scaleCopy(double scaleFactor)
    {
        return new Vector(this.i * scaleFactor, this.j * scaleFactor);
    }
    public Vector scale(double scaleFactor)
    {
        this.i *= scaleFactor;
        this.j *= scaleFactor;
        return this;
    }
    
    /**
     * unitVector()
     * 
     * Create the unit vector in the same direction as this.
     * 
     * @return  Vector  a unit vector in the same direction as this
     */
    public Vector unitVectorCopy()
    {
        return this.scaleCopy(1 / this.magnitude());
    }
    public Vector unitVector()
    {
        return this.scale(1 / this.magnitude());
    }
    
    /**
     * componentInDirection(Vector v)
     * 
     * Calculate and return the component of this in the direction of v
     * 
     * @param   Vector  a vector
     * @return  Vector  the component of this in the same direction as v
     */
    public Vector componentInDirection(Vector v)
    {
        double m = v.magnitude();
        this.copy(v.scaleCopy(this.dot(v) / (m * m)));
        return this;
    }
    public Vector componentInDirectionCopy(Vector v)
    {
        double m = v.magnitude();
        return v.scaleCopy(this.dot(v) / (m * m));
    }
    
    /**
     * normal()
     * 
     * Calculate and return a vector normal to this one
     * 
     * @return  Vector  a normal vector
     */
    public Vector normalCopy()
    {
        return new Vector(-1 * this.j, this.i);
    }
    public Vector normal()
    {
        double temp = this.i;
        this.i = -1 * this.j;
        this.j = temp;
        return this;
    }
    
    // Public getters
    public double getI()
    {
        return this.i;
    }
    public double getJ()
    {
        return this.j;
    }
    public void setI(double i)
    {
        this.i = i;
    }
    public void setJ(double j)
    {
        this.j = j;
    }
    /**
     * toString()
     * 
     * Creates a string formatted "<i, j>"
     * 
     * @return  String  the string representation of this vector
     */
    public String toString()
    {
        return "<" + this.i + ", " + this.j + ">";
    }
}
