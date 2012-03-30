 

  

/**
 * A point with double coordinates
 * 
 * @author Brendan Redmond
 * @version 0.2.0
 */
public class Location
{
    //the x coordinate of this Location
    private double x;
    //the y coordinate of this Location
    private double y;
    
    /**
     * Constructor: defaults x and y to 0
     */
    public Location()
    {
        this.x = 0.0;
        this.y = 0.0;
    }
    
    /**
     * Constructor: sets x and y to given values
     * 
     * @param x     the given x value
     * @param y     the given y value
     */
    public Location(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY()
    {
        return this.y;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
}
