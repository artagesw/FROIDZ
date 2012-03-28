package workbench;
import java.io.File;
import robot.*;
import java.util.ArrayList;
import org.w3c.dom.*;
/**
 * This class is meant to be used to import a folder of users folders, which
 * each containing a list of XML files that themselves contain the specifications
 * necessary to construct a Robot, and to take that information and turn it into an
 * ArrayList of Robots which can be used for a variety of purposes.
 * 
 * @author Charlie Beckett
 * @version 0.0.001
 */
public class RobotBuilder
{
    private ArrayList<Robot> robotList = new ArrayList();
    /**
     * Constructor for objects of class RobotBuilder
     */
    public RobotBuilder()
    {
        //Receive the folder of all the users
        File folder = new File("users");
        File notToBeAssembled = new File("users/PlayingRobots.xml");
        File[] userTempList = folder.listFiles();
        ArrayList<File> userList = new ArrayList(userTempList.length -1);
        for (int i = 0; (i < userTempList.length - 1) && (userTempList[i].getPath().compareTo(notToBeAssembled.getPath()) != 0); i++)
        {
            userList.add(userTempList[i]);
        }
        for (File f : userList) 
        {
            File notToBeAssembled2 = new File(f.getPath() + "/user.xml");
            File[] userRobotList = f.listFiles();
            for (int i = 0; (i < userRobotList.length) && (userRobotList[i].getPath().compareTo(notToBeAssembled2.getPath()) != 0); i++)
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
        Robot tempRobot = new Robot(f.toString());
        XMLFile robotFile = new XMLFile(f);
        NodeList partList = robotFile.getElementsByTagName("Part");
        for (int i = 0; i < partList.getLength(); i++)
        {
            Node partCode = partList.item(i);
            Part toBeBuilt = new Part();
            tempRobot.addPart(toBeBuilt);
        }
        return tempRobot;
    }

    //setter/getter methods
    public void setRobots(ArrayList <Robot> robots)
    {
        robotList = robots;
    }
    
    public ArrayList<Robot> getRobots()
    {
        return robotList;
    }
}
