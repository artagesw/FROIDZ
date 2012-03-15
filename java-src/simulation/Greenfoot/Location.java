  

/**
 * A point with double coordinates
 * 
 * @author Brendan Redmond
 * @version 1.0.0
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
