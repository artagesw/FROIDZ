import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import workbench.Builder;
import robot.Robot;
import emulator.cpu.*;
import java.awt.Color;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Ellipse2D;
/**
 * A battle arena.
 * 
 * @author Brendan Redmond and Haley B-E
 * @version 0.2.0
 */
public class Arena extends World
{
    //the width of the arena in cells
    public static final int WIDTH = 600;
    //the height of the arena in cells
    public static final int HEIGHT = 600;
    //the maximum number of robots in this Arena
    public static final int MAX_ROBOTS = 8;
    //the maximum height of the robot in pixels
    public static final int MAX_ROBOT_HEIGHT = 50;
    //the maximum width of the robot in pixels
    public static final int MAX_ROBOT_WIDTH = 50;    
    //length of time to be given as a turn
    private static final int TURN_LENGTH = 1;

    
    /**
     * Constructor: creates the arena, then adds the robots to the arena
     * 
     */
    public Arena()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        this.setActOrder(RobotActor.class, Wall.class, ArenaActor.class);
        
        Builder builder = new Builder();
        ArrayList<Robot> robots = builder.getRobots();
        ArrayList<Location> spawnLocations = SpawnMap.getSpawnLocations(robots.size());
        
        for (Robot robot : robots)
        {
            Location startLocation = spawnLocations.remove((int) (Math.random() * spawnLocations.size()));
            RobotActor robotActor = new RobotActor(robot);
            
            //adds each robot to a random spawn location and removes that spawn location
            this.add(robotActor, startLocation);
            
            robotActor.setRotation((int) (Math.random() * 360));
            robotActor.setImage("images/TestBot.png");
            //robot.setExactRotation(robot.getAngleTowards((this.getWidth() / 2), (this.getHeight() / 2)));
        }
        
        this.makeWalls();
        
        this.setBackground("images/cell.jpg");
    }
    
    private GreenfootImage makeRobotImage()
    {
        GreenfootImage pic = new GreenfootImage(MAX_ROBOT_WIDTH, MAX_ROBOT_HEIGHT);
        
        pic.setColor(Color.BLACK);
        pic.fillShape(new Ellipse2D.Double(20, 20, MAX_ROBOT_WIDTH - 20, MAX_ROBOT_HEIGHT - 20));
        pic.setColor(Color.RED);
        pic.fillOval(((MAX_ROBOT_WIDTH * 7) / 8) - 5, ((MAX_ROBOT_HEIGHT * 1) / 2) - 5, 10, 10);
        
        return pic;
    }
    
    /**
     * Creates the walls that bound the Arena
     */
    private void makeWalls()
    {
        int t = 30;
        int d = 90;
        Wall wall = new Wall(new int[]{d,0},
                             new int[]{WIDTH,0},
                             new int[]{WIDTH,HEIGHT},
                             new int[]{0,HEIGHT},
                             new int[]{0,0},
                             new int[]{d,0},
                             new int[]{d,t},
                             new int[]{t,d},
                             new int[]{t,HEIGHT - d},
                             new int[]{d,HEIGHT - t},
                             new int[]{WIDTH - d,HEIGHT - t},
                             new int[]{WIDTH - t,HEIGHT - d},
                             new int[]{WIDTH - t,d},
                             new int[]{WIDTH - d,t},
                             new int[]{d,t});
                             
        this.addObject(wall, WIDTH / 2, HEIGHT / 2);
        
        /*
        Wall blocky = new Wall(new int[]{0,0},
                               new int[]{100,0},
                               new int[]{150,50},
                               new int[]{150,200},
                               new int[]{100,250},
                               new int[]{50,300},
                               new int[]{0,100});
        this.addObject(blocky, WIDTH / 2, HEIGHT / 2);       
        
        
        Wall blocky = new Wall(new int[]{50,0},
                          new int[]{300,0},
                          new int[]{350,50},
                          new int[]{350,300},
                          new int[]{325,300},
                          new int[]{325,50},
                          new int[]{25,50},
                          new int[]{25,300},
                          new int[]{0,300},
                          new int[]{0,50},
                          new int[]{50,0});
        this.addObject(blocky, WIDTH / 2, HEIGHT / 2);
        */
    }
    
    /**
     * 
     */
    public void act()
    {
    }
    
    //Methods for adding/removing things to the arena
    
    /**
     * Wrapper method for addObject, takes an Actor and a Location
     * 
     * @param actor     the actor to be added
     * @param location  the location at which the actor will be added
     */
    private void add(ArenaActor actor, Location location)
    {
        this.addObject(actor, (int) (location.getX() + .5), (int) (location.getY() + .5));
        actor.setLocation(location);
    }
    
    /**
     * Wrapper method for addObject, takes an Actor, an x and a y coordinate
     * 
     * @param actor     the actor to be added
     * @param x         the x coordinate of the location at which the actor will be added
     * @param y         the y coordinate of the location at which the actor will be added
     */
    public void add(ArenaActor actor, double x, double y)
    {
        this.addObject(actor, (int) (x + .5), (int) (y + .5));
        actor.setLocation(new Location(x, y));
    }  
    
    /**
     * Removes given ArenaActor from arena
     * 
     * @param toRemove      ArenaActor to remove from arena
     */
    public void remove(ArenaActor toRemove)
    {
        this.removeObject(toRemove);
    }
    
    //Methods for ArenaActor interaction
    
    /**
     * Finds the distance between the starting point and the nearest ArenaActor
     * 
     * @param direction         direction in which to search for ArenaActor (angle in degrees)
     * @param startingPoint     point of origin for search
     * @return                  distance between starting point and nearest ArenaActor
     */
    public int getNearest(int direction, int startingPoint)
    {
        return 0;
    }
    
    /**
     * Finds the distance between starting point and the nearest ArenaActor within a given range of directions
     *              (for a field of vision rather than a line of sight)
     * Precondition: startDirection + tolerance <= endDirection
     * 
     * @param startDirection        the beginning of the range in which to look for an ArenaActor (angle in degrees)
     * @param endDirection          the end of the range in which to look for an ArenaActor (angle in degrees)
     * @param startingPoint         point of origin for search
     * @param tolerance             distance between each search (angle in degrees)
     * @return                      distance between starting point and nearest ArenaActor within range
     */
    public int getNearestInRange(int startDirection, int endDirection, int startingPoint, int tolerance)
    {
        int nearest = this.getNearest(startDirection, startingPoint);
        for (int i = (startDirection + tolerance); i <= endDirection; i += tolerance)
        {
            int temp = this.getNearest(i, startingPoint);
            if (temp < nearest)
            {
                nearest = temp;
            }
        }
        return nearest;
    }
    
    //methods that ensure given coordinates are within the Arena

    public static boolean xIsInBoundaries(double x)
    {
        return ((x >= 0) && (x <= Arena.WIDTH));
    }
   
    public static boolean yIsInBoundaries(double y)
    {
        return ((y >= 0) && (y <= Arena.WIDTH));
    }
    
    public static boolean isInBoundaries(double x, double y)
    {
        return (xIsInBoundaries(x) && yIsInBoundaries(y));
    }
}
