package robot;  

import java.util.ArrayList;
import emulator.cpu.FROIDZCPU;

/**
 * Main robot class.
 * 
 * @author Sam Weiss
 * @version 0.1.0
 */
public class Robot
{
    private String name;                    // robot's name
    private FROIDZCPU cpu;
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

    public Robot setCPU(FROIDZCPU cpu)
    {
        this.cpu = cpu;
        return this;
    }
    public FROIDZCPU cpu()
    {
        return this.cpu;
    }
    
    public Robot addPart(Part part)
    {
        this.parts.add(part);
        this.cpu.connectToSerial(part, part.getSerialPort());
        return this;
    }
}
