 

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
    
    public double getAngleTowards(Location loc)
    {
        return this.getAngleTowards(loc.getX(), loc.getY());
    }
    
    public double getAngleTowards(double x, double y)
    {
        double changeX = x - this.getX();
        double changeY = y - this.getY();
        
        return (Math.atan(changeX / changeY) * (180 / Math.PI));
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
