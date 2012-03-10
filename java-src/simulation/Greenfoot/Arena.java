import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A battle arena.
 * 
 * @author Brendan Redmond
 * @version 0.1.0
 */
public class Arena extends World
{
    //the width of the arena
    private static final int WIDTH = 600;
    //the height of the arena
    private static final int HEIGHT = 400;

    /**
     * Constructor: creates the arena, then adds the robots to the arena
     * 
     */
    public Arena()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        Builder builder = new Builder();
        
        for (Robot robot : builder.getRobots())
        {
            this.add(robot);
        }
    }
    
    /**
     * Adds a robot to a random empty location.
     * 
     * @param robot     the robot to added
     */
    public void add(Robot robot)
    {
        this.addObject(robot, (int) Math.random() * WIDTH, (int) Math.random() * HEIGHT);
        
        while (robot.isInContact())
        {    
            robot.setLocation((int) Math.random() * WIDTH, (int) Math.random() * HEIGHT);
        }
    }
}
