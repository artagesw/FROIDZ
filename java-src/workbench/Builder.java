package workbench;

import java.util.ArrayList;
import org.w3c.dom.*;
import java.io.File;
import robot.*;
import emulator.cpu.*;

/**
 * Builds robots from the directory tree "users/" 
 * 
 * @author Henry Millican
 * @version 0.0.3
 */
public class Builder
{
    XMLFile playingRobots; //xml file containing the names of all playing robots
    File usersDirectory; //our root directory, "users/"
    
    /**
     * Constructor for the builder class, 
     * sets up root file directory, 
     * instantiates the playingRobots file
     */
    public Builder()
    {
        this.usersDirectory = new File("users");        
        for (File f : this.usersDirectory.listFiles())
        {
            if (f.getName().equals("PlayingRobots.xml"))
            {
                this.playingRobots = new XMLFile(f);
            }           
        }
    }
    
    /**
     * Gets the fully constructed Robots. 
     * Iterates through the user directories to find playing robots
     * 
     * @return      ArrayList of Robots
     */
    public ArrayList<Robot> getRobots()
    {
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        String listOfPlayingRobots  = this.playingRobots.getDocumentElement().getTextContent();
        for (File userDirectory : usersDirectory.listFiles())
        {
            if (!userDirectory.getName().equals("PlayingRobots.xml"))
            {
                System.out.println(userDirectory);
                for (File userFile : userDirectory.listFiles())
                {
                    if (!userFile.getName().equals("user.xml")  && !userFile.getName().contains(".tst") && listOfPlayingRobots.contains(userFile.getName()))
                    {
                        robotList.add(buildRobot(new XMLFile(userFile)));
                    }
                }
            }
        }
        return robotList;
    }
    
    /**
     * Builds a single robot
     * 
     * @param robotFile     the XML file containing all the robot properties
     * @return              completed robot
     */
    private Robot buildRobot(XMLFile robotFile)
    {
        //////////////create a robot/////////////////////
        String name = robotFile.getDocumentElement().getElementsByTagName("Name").item(0).getTextContent();
        Robot robot = new Robot(name);
        /////////////////////////////////////////////////
        
        //////////////////build/code cpu ////////////////
        String CPUName = robotFile.getDocumentElement().getElementsByTagName("CPU").item(0).getTextContent();
        String codePath = (robotFile.toString().substring(0, (robotFile.toString().length()-4)) + ".tst");
        robot.setCPU(this.buildCPU(CPUName, codePath));
        /////////////////////////////////////////////////   
                        
        /////////////build and add parts/////////////////
        ArrayList<Part> parts = this.buildParts(robotFile.getDocumentElement().getElementsByTagName("Part"));
        for (Part p : parts)
        {
            robot.addPart(p);
        }
        /////////////////////////////////////////////////
        
        return robot;
    }
    
    /**
     * Builds the CPU for the robot
     * 
     * @param CPUName       the name of the class of CPU to construct
     * @param codePath      path to assembled robot code
     * @return              completed CPU
     */
    private FROIDZCPU buildCPU(String CPUName, String codePath)
    {
        try 
        {
            Class c = Class.forName("emulator.cpu." + CPUName); 
            FROIDZCPU cpu = (FROIDZCPU)c.getConstructor(new Class[]{String.class}).newInstance(codePath);
            return cpu;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Creates a list of the robot's parts
     * 
     * @param nl        NodeList of elements from the robot's xml file with the tag name "part"
     * @return          ArrayList of assembled robot parts
     */
    private ArrayList<Part> buildParts(NodeList nl)
    {
        ArrayList<Part> partsList = new ArrayList<Part>();
        for (int i = 0; i < nl.getLength(); i++)
        {
            Element partElement = (Element)nl.item(i);
            int portNumber = Integer.valueOf(partElement.getElementsByTagName("Port").item(0).getTextContent());
            String partName = partElement.getElementsByTagName("Name").item(0).getTextContent();
            String partClass = partElement.getElementsByTagName("Class").item(0).getTextContent();
            try
            {
                Class c = Class.forName("robot." + partClass);
                Part p = (Part)c.newInstance();
                p.setSerialPort(portNumber);
                p.setName(partName);
                partsList.add(p);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        return partsList;
    }
    
    /*belongs in store where code will be built and the bin will be linked
    private String buildRobotCode()
    {
        return "";
    }*/
}
