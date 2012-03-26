package workbench;
import java.io.File;
//import org.w3c.dom.Document;
//import org.w3c.dom.*;
import robot.robot.*;
import java.util.ArrayList;
/**
 * This class is meant to be used to import a folder of users folders, which
 * each containing a list of XML files that themselves contain the specifications
 * necessary to construct a Robot, and to take that information and turn it into an
 * ArrayList of Robots which can be used for a variety of purposes.
 * 
 * @author Charlie Beckett
 * @version 0.0.001
 */
public class RobotAssembler
{
    private ArrayList<Robot> robotList = new ArrayList();
    /**
     * Constructor for objects of class RobotAssembler
     */
    public RobotAssembler()
    {
        //Receive the folder of all the users
        File folder = new File("C:/users/Charlie/dropbox/graphicsTester/user");
        File[] userList = folder.listFiles();
        for (File f : userList) 
        {
            
            File[] userRobotList = f.listFiles();
            for (int i = 0; (i < userRobotList.length) && (userRobotList[i].getParent().compareTo("PlayingRobots.xml") == 0); i++)
            {
                if (userRobotList[i].getName() != "user.xml")
                {
                    robotList.add(this.build(userRobotList[i]));
                }
            }
        }
    }
    
    /**
     * Takes an XML file containing the specifications necessary to build a robot,
     * and then attempts to construct and configure the robot according to the 
     * specifications.
     * 
     * @param   f   The XML file containing the specifications necessary for a 
     *                  robot
     * @return      Returns the robot constructed and configured to spec.
     */
    public Robot build(File f)
    {
        Robot tempRobot = new Robot();
        return tempRobot;
    }

}
