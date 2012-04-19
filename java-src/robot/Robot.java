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
    private int health;                        // overall robot health
    private double speed;                      // current speed in meters/sec.
    private double rotationalVelocity;         // current rotational velocity in deg/sec
    private ArrayList<RobotAction> actionList;

    /**
     * Constructor for objects of class Robot
     */
  
    public Robot(String name)
    {
        this.setName(name);
        this.parts = new ArrayList<Part>();
        this.health = 100;
        this.actionList = new ArrayList<RobotAction>();
    }
    
    public Robot setName(String name)
    {
        this.name = name;
        return this;
    }
    public String name()
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
        part.setRobot(this);
        this.parts.add(part);
        this.cpu.connectToSerial(part, part.getSerialPort());
        return this;
    }
    
    public void setSpeed (double curSpeed)
    {
        this.speed = curSpeed;
    }
    
    public double getSpeed()
    {
        return this.speed;
    }
    
    public void setRotationalVelocity(double v)
    {
        this.rotationalVelocity = v;
    }
    
    public double getRotationalVelocity()
    {
        return this.rotationalVelocity;
    }
    
    public void inflictDamage (int damage)
    {
        // build list of hit weights for our parts
        

        for (Part part : this.parts)
        {
        }
    }

    public void launchProjectile(int kind, double radius, double mass, double speed)
    {
        System.out.println("LAUNCH PROJECTILE kind(" + kind  + ") radius(" + ") mass(" + ") speed(" + speed + ")");
        RobotAction action = new LaunchAction(kind, radius, mass, speed);
        this.actionList.add(action);
    }
    
    public ArrayList<RobotAction> act(int timeInMS)
    {
        this.actionList.clear();
 
        if (this.health > 0)
        {
            this.cpu.act(timeInMS);
        
            for (Part part : this.parts)
            {
                part.act();
            }
        }
        
        return this.actionList;
    }
}
