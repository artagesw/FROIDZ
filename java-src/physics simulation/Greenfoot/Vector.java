 

/**
 * A generic 2d vector. Immutable/
 * 
 * @author Jacob Weiss
 * @version 0.0.1
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
    
    public void copy(Vector v)
    {
        this.i = v.i;
        this.j = v.j;
    }
    
    /**
     * add(Vector v)
     * 
     * Add vector v to this. Return the result.
     * 
     * @param   Vector  the vector to add to this
     * @return  Vector  the resultant vector
     */
    public Vector add(Vector v)
    {
        return new Vector(this.i + v.i, this.j + v.j);
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
    public Vector scale(double scaleFactor)
    {
        return new Vector(this.i * scaleFactor, this.j * scaleFactor);
    }
    
    /**
     * unitVector()
     * 
     * Create the unit vector in the same direction as this.
     * 
     * @return  Vector  a unit vector in the same direction as this
     */
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
        return v.scale(this.dot(v) / (m * m));
    }
    
    /**
     * normal()
     * 
     * Calculate and return a vector normal to this one
     * 
     * @return  Vector  a normal vector
     */
    public Vector normal()
    {
        return new Vector(-1 * this.j, this.i);
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
