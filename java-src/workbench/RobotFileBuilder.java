package workbench;

import java.io.File;
import org.w3c.dom.*;
/**
 * Bunch of methods for adding/removing parts and other robot attributes
 * to a user's robot's xml file specified in the constructor parameters. 
 * 
 * @author Henry Millican
 * @version 0.0.2
 */
public class RobotFileBuilder
{
    private XMLFile robotFile;
    private Element robotFileRootElement;
    /**
     * Creates a RobotFileBuilder, will also create the robot's xml
     * file is it doesn't exist. 
     * 
     * @param userName      username of the player's robot to modify, this is used to find the folder
     * @param robotName     name of the robot file to ceate/change
     */
    public RobotFileBuilder(String userName, String robotName)
    {
        assert userName != null && !userName.equals("");
        assert robotName != null && !robotName.equals("");
        
        File userDirectory = new File("users");    
        userDirectory = new File(userDirectory, userName);
        assert userDirectory.exists();
        
        File[] robots = userDirectory.listFiles();
        this.robotFile = new XMLFile(new File(userDirectory, robotName));
        boolean robotFound = false;
        
        for (File f : robots)
        {
            if (f.getName().equals(robotName))
            {                
                robotFound = true;
                break;
            }
        }
    
        this.robotFileRootElement = this.robotFile.getDocumentElement();
        if (!robotFound)
        {   
            Element nameElement = this.robotFile.createElement("Name");
            nameElement.setTextContent(robotName);
            
            this.robotFileRootElement.appendChild(nameElement);
            this.robotFileRootElement.appendChild(this.robotFile.createElement("CPU"));
            this.robotFileRootElement.appendChild(this.robotFile.createElement("Image"));
            this.robotFileRootElement.appendChild(this.robotFile.createElement("Code"));
            this.robotFile.write();
        }
    }
    
    /**
     * Creates another element in the document tree for a part, adds part and part properties 
     * 
     * @param partClassName     name of the part's class to add
     * @param port              the port the part will connect to 
     */
    public void addPart(String partClassName, int port)
    {
        assert partClassName != null && !partClassName.equals("");
        assert port >= 0;
        
        Element partElement = this.robotFile.createElement("Part");
        
        Element partPortElement = this.robotFile.createElement("Port");
        partPortElement.setTextContent(Integer.toString(port));
        
        Element partNameElement = this.robotFile.createElement("Name");
        partNameElement.setTextContent(partClassName);
        
        Element partClassElement = this.robotFile.createElement("Class");
        partClassElement.setTextContent(partClassName);
        
        partElement.appendChild(partPortElement);
        partElement.appendChild(partNameElement);
        partElement.appendChild(partClassElement);
        
        this.robotFileRootElement.appendChild(partElement);
        this.robotFile.write();
    }
    
    /**
     * Removes the part (and the element) given by the parameter
     * 
     * @param partClassName     name of the part's class you wish to remove
     * @return                  name of the part removed
     */
    public String removePart(String partClassName)
    {
        assert partClassName != null && !partClassName.equals("");
        
        NodeList parts = this.robotFile.getElementsByTagName("Part");
        for (int i = 0; i < parts.getLength(); i++)
        {
            Element part = (Element)parts.item(i);
            if (part.getElementsByTagName("Class").item(0).getTextContent().equals(partClassName))
            {
                this.robotFileRootElement.removeChild(parts.item(i));
                this.robotFile.write();
                return partClassName;
            }
        }
        return null;
    }
    
    /**
     * Adds the name of the CPU's class to the CPU element
     * 
     * @param CPUClassName      name of the CPU's class (omit .class)
     */
    public void setCPU(String CPUClassName)
    {
        assert CPUClassName != null && !CPUClassName.equals("");       
        this.robotFile.getElementsByTagName("CPU").item(0).setTextContent(CPUClassName);
        this.robotFile.write();
    }
    
    /**
     * Adds the path to the robot's image into the image element
     * 
     * @param imgPath   path to the image
     */
    public void setImage(String imgPath)
    {
        assert imgPath != null && !imgPath.equals("");
        this.robotFile.getElementsByTagName("Image").item(0).setTextContent(imgPath);
        this.robotFile.write();
    }
    
    /**
     * Adds the given compiled robot code to the robot xml file
     * (later will compile, waiting on a method from alex)
     * 
     * @param code      string representation of assembled robot code
     */
    public void setCode(String code)
    {
        assert code != null && !code.equals("");
        this.robotFile.getElementsByTagName("Code").item(0).setTextContent(code);
        this.robotFile.write();
    }
}
