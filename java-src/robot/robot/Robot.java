<<<<<<< HEAD
package robot;  

import java.util.ArrayList;
import emulator.cpu.FROIDCPU;

=======
package robot.robot;  
   
>>>>>>> 5f2507502062dfcecd5f7c1e7ec12e9791e75428
/**
 * Main robot class.
 * 
 * @author Sam Weiss
 * @version 0.1.0
 */
public class Robot
{
    private String name;                    // robot's name
    private FROIDCPU cpu;
    private ArrayList<Part> parts;

    /**
     * Constructor for objects of class Robot
     */
  
    public Robot(String name)
    {
        this.setName(name);
        this.parts = new ArrayList<Part>();
    }
    
    public Robot setName(String name)
    {
        this.name = name;
        return this;
    }
    public String name(String name)
    {
        return this.name;
    }

    public Robot setCPU(FROIDCPU cpu)
    {
        this.cpu = cpu;
        return this;
    }
    public FROIDCPU cpu()
    {
        return this.cpu;
    }
    
    public Robot addPart(Part part)
    {
        this.parts.add(part);
        return this;
    }
}
