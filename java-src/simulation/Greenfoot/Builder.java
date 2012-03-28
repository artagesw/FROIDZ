import java.util.ArrayList;

/**
 * Stub class - placeholder for Charlie and Henry's class
 * 
 * @author Haley B-E and Brendan Redmond
 * @version 0.1.0
 */
public class Builder  
{
    public Builder()
    {
        
    }
    
    public ArrayList<RobotActor> getRobots()
    {
        ArrayList<RobotActor> robots = new ArrayList<RobotActor>();
        for (int i = 0; i < 2; i++)
        {
            robots.add(new RobotActor());
        }
        
        return robots;
    }
}
