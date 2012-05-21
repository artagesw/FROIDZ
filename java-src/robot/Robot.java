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
    private String name;                       // robot's name
    private FROIDZCPU cpu;
    private ArrayList<Part> parts;
    private int maxDamageAllocationRange;
    private ArrayList<Integer> damageAllocationRanges;  // array of ranges of damage weights
    private int maxHealth;                        // sum of the starting health of all Parts
    private double speed;                      // current speed in meters/sec.
    private double rotationalVelocity;         // current rotational velocity in deg/sec
    private ArrayList<RobotAction> actionList;
    private boolean cpuDead = false;    

    /**
     * Constructor for objects of class Robot
     */
  
    public Robot(String name)
    {
        this.setName(name);
        this.parts = new ArrayList<Part>();
        this.damageAllocationRanges = new ArrayList<Integer>();
        this.maxDamageAllocationRange = 0;
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
        this.addPart(new CPUPart());
        return this;
    }
 
    public FROIDZCPU getCPU()
    {
        return this.cpu;
    }
    
    public void killCPU()
    {
        this.cpuDead = true;
    }
    
    public void pokeCPU()
    {
        this.cpuDead = false;
    }
    
    public Robot addPart(Part part)
    {
        part.setRobot(this);
        this.parts.add(part);
        this.maxDamageAllocationRange += part.getDamageWeight();
        this.damageAllocationRanges.add(this.maxDamageAllocationRange);
        this.maxHealth += part.getHealth();
        System.out.println("Connecting part to port #" + part.getSerialPort());
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
    
    public double getHealth()
    {
        int currentHealth = 0;
        for (Part p : parts)
        {
            currentHealth += p.getHealth();
        }
        return ((currentHealth / this.maxHealth) * 100);
    }
    
    public void inflictDamage (int damage)
    {
        // find the part onto which to inflict damage
        
        int probability = (int)(Math.random() * this.maxDamageAllocationRange);
        
        for (int i = 0; i < this.damageAllocationRanges.size(); ++i)
        {
            if (probability < this.damageAllocationRanges.get(i))
            {
                this.parts.get(i).inflictDamage(damage);
                System.out.println("Damage to " + this.parts.get(i).getName() + "Health remaining: " + this.parts.get(i).getHealth());
                break;
            }
        }
    }

    public void launchProjectile(int kind, double radius, double mass, double speed)
    {
        System.out.println("Launching projectile!");

        System.out.println("LAUNCH PROJECTILE kind(" + kind  + ") radius(" + radius + ") mass(" + mass + ") speed(" + speed + ")");

        RobotAction action = new LaunchAction(kind, radius, mass, speed);
        this.actionList.add(action);
    }
    
    public ArrayList<RobotAction> act(int timeInMS)
    {
        this.actionList.clear();
 
        if (!cpuDead)
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
