package workbench;
import java.io.File;
import org.w3c.dom.*;
/**
 * Write a description of class RobotFileBuilder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RobotFileBuilder
{
    private XMLFile robotFile;
    /**
     * Constructor for objects of class RobotFileBuilder
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
        
        if (!robotFound)
        {   
            Element nameElement = this.robotFile.createElement("Name");
            nameElement.setTextContent(robotName);
         
            this.robotFile.appendChild(nameElement);
            this.robotFile.appendChild(this.robotFile.createElement("CPU"));
            this.robotFile.appendChild(this.robotFile.createElement("Image"));
            this.robotFile.appendChild(this.robotFile.createElement("Code"));
        }
        
    }
    
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
        
        this.robotFile.appendChild(partElement);
        this.robotFile.write();
    }
    
    public String removePart(String partClassName)
    {
        assert partClassName != null && !partClassName.equals("");
        
        NodeList parts = this.robotFile.getElementsByTagName("Part");
        for (int i = 0; i < parts.getLength(); i++)
        {
            if (parts.item(i).getTextContent().equals(partClassName))
            {
                this.robotFile.removeChild(parts.item(i));
                return partClassName;
            }
        }
        this.robotFile.write();
        return null;
    }
    
    public void setCPU(String CPUClassName)
    {
        assert CPUClassName != null && !CPUClassName.equals("");       
        this.robotFile.getElementsByTagName("CPU").item(0).setTextContent(CPUClassName);
        this.robotFile.write();
    }
    
    public void setImage(String imgPath)
    {
        assert imgPath != null && !imgPath.equals("");
        this.robotFile.getElementsByTagName("Image").item(0).setTextContent(imgPath);
        this.robotFile.write();
    }
    
public void setCode(String code)
    {
        assert code !=null && !code.equals("");
        this.robotFile.getElementsByTagName("Code").item(0).setTextContent(code);
        this.robotFile.write();
    }
}
