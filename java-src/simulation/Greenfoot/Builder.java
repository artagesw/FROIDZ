import java.util.ArrayList;

import robot.Robot;
/**
 * Stub class.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Builder  
{
    public Builder()
    {
        
    }
    
    public ArrayList<Robot> getRobots()
    {
        ArrayList<Robot> robots = new ArrayList<Robot>();
        robots.add(new Robot());
        robots.add(new Robot());
        
        return robots;
    }
}
