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
    File wallsDirectory;
    /**
     * Constructor for the builder class, 
     * sets up root file directory, 
     * instantiates the playingRobots file
     */
    public Builder()
    {
        this.usersDirectory = new File("users");        
        this.wallsDirectory = new File("walls");
        for (File f : this.usersDirectory.listFiles())
        {
            if (f.getName().equals("PlayingRobots.xml"))
            {
                this.playingRobots = new XMLFile(f);
            }           
        }
    }
    /*
    public ArrayList<Obstacle> getStationaryObstacles()
    {
        File[] stationaryObstacleFiles = new File(this.wallsDirectory, "stationary obstacles").listFiles(new WallFilter());
        
        ArrayList<Obstacle> stationaryObstacles = new ArrayList<Obstacle>();
        for (File f : stationaryObstacleFiles)
        {
            XMLFile file = new XMLFile(f);
            NodeList pointElements = file.getElementsByTagName("Point");
            int[][] points = new int[pointElements.getLength()][];
            
            for (int i = 0; i < pointElements.getLength(); i++)
            {
                Element e = (Element)pointElements.item(i);
                
                int[] point = new int[2];
                /////////////////////////////////////////////////////////////
                Element xCoord = (Element)e.getElementsByTagName("X").item(0);
                point[0] = Integer.valueOf(xCoord.getTextContent());
                Element yCoord = (Element)e.getElementsByTagName("Y").item(0);
                point[1] = Integer.valueOf(yCoord.getTextContent());
                //////////////////////////////////////////////////////////////
                points[i] = point;
            }
            stationaryObstacles.add(new Obstacle(points));
        }
        return stationaryObstacles;
    }
    
    public ArrayList<MovingObstacle> getMovingObstacles()
    {
        File[] movingObstacleFiles = new File(this.wallsDirectory, "moving obstacles").listFiles(new WallFilter());
        ArrayList<MovingObstacle> obstacles = new ArrayList<MovingObstacle>();
        
        for (File f : movingObstacleFiles)
        {
            XMLFile file = new XMLFile(f);
            NodeList pointElements = file.getElementsByTagName("Point");
            int[][] points = new int[pointElements.getLength()][];
            for (int i = 0; i < pointElements.getLength(); i++)
            {
                Element e = (Element)pointElements.item(i);
                int[] point = new int[2];
                /////////////////////////////////////////////////////////////
                Element xCoord = (Element)e.getElementsByTagName("X").item(0);
                point[0] = Integer.valueOf(xCoord.getTextContent());
                Element yCoord = (Element)e.getElementsByTagName("Y").item(0);
                point[1] = Integer.valueOf(yCoord.getTextContent());
                //////////////////////////////////////////////////////////////
                points[i] = point;
            }
            
            NodeList vectorPath = ((Element)file.getElementsByTagName("Vectors").item(0)).getElementsByTagName("Vector");
            Vector[] vectorList = new Vector[vectorPath.getLength()];
            for (int i = 0; i < vectorPath.getLength(); i++)
            {
                Element vectorElement = (Element)vectorPath.item(i); //get the entire vector element
                
                //get the i and j components
                int iComponent = Integer.valueOf(((Element)vectorElement).getElementsByTagName("i").item(0).getTextContent());
                int jComponent = Integer.valueOf(((Element)vectorElement).getElementsByTagName("j").item(0).getTextContent());
                
                //instantiate vector and add it to the list
                Vector vector = new Vector(iComponent, jComponent);
                vectorList[i] = vector;
            }
            
            
            MovingObstacle obstacle = new MovingObstacle(points);
            obstacles.add(obstacle);
        }
        return obstacles;    
    }
    
    public void setMovingObstacleProperties(ArrayList<MovingObstacle> obstacles)
    {
        File[] movingObstacleFiles = new File(this.wallsDirectory, "moving obstacles").listFiles(new WallFilter());   
        assert obstacles.size() == movingObstacleFiles.length: "STOP CHANGING FILES WHILE THIS IS RUNNING";
        for (int i = 0; i < movingObstacleFiles.length; i++)
        {
            XMLFile file = new XMLFile(movingObstacleFiles[i]);
            NodeList vectorPath = ((Element)file.getElementsByTagName("Vectors").item(0)).getElementsByTagName("Vector");
            Vector[] vectorList = new Vector[vectorPath.getLength()];
            for (int j = 0; i < vectorPath.getLength(); j++)
            {
                Element vectorElement = (Element)vectorPath.item(i); //get the entire vector element
                
                //get the i and j components
                int iComponent = Integer.valueOf(((Element)vectorElement).getElementsByTagName("i").item(0).getTextContent());
                int jComponent = Integer.valueOf(((Element)vectorElement).getElementsByTagName("j").item(0).getTextContent());
                
                //instantiate vector and add it to the list
                Vector vector = new Vector(iComponent, jComponent);
                vectorList[j] = vector;
            }
            int rotationalVelocity = Integer.valueOf(((Element)file).getElementsByTagName("RotationalVelocity").item(0).getTextContent());
            int speed = Integer.valueOf(((Element)file).getElementsByTagName("Speed").item(0).getTextContent());
            int rotation = Integer.valueOf(((Element)file).getElementsByTagName("Rotation").item(0).getTextContent());
            obstacles.get(i).setRotationalVelocity(rotationalVelocity);
            obstacles.get(i).setSpeed(speed);
            obstacles.get(i).setRotation(rotation);
            obstacles.get(i).setPath(vectorList);
        }

    }
    */
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
        for (File userDirectory : usersDirectory.listFiles(new FolderOnlyFilter()))
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
            int damageWeight = Integer.valueOf(partElement.getElementsByTagName("DamageWeight").item(0).getTextContent());
            try
            {
                Class<?> c = Class.forName("robot." + partClass);
                Part p = (Part)c.newInstance();
                if (p instanceof LauncherPart)
                {
                    LauncherPart lp = (LauncherPart)p;
                    
                    lp.setKind(Integer.valueOf(partElement.getElementsByTagName("Kind").item(0).getTextContent()));
                    lp.setRounds(Integer.valueOf(partElement.getElementsByTagName("Rounds").item(0).getTextContent()));
                    lp.setRadius(Double.valueOf(partElement.getElementsByTagName("Radius").item(0).getTextContent()));
                    lp.setMass(Double.valueOf(partElement.getElementsByTagName("Mass").item(0).getTextContent()));
                    lp.setSpeed(Double.valueOf(partElement.getElementsByTagName("Speed").item(0).getTextContent()));   
                    
                    lp.setDamageWeight(damageWeight);
                    lp.setSerialPort(portNumber);
                    lp.setName(partName);
                    
                    partsList.add(lp);
                }
                else
                {
                    p.setSerialPort(portNumber);
                    p.setName(partName);
                    p.setDamageWeight(damageWeight);
                    partsList.add(p);
                }

            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        return partsList;
    }
}
