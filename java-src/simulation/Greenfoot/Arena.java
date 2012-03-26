import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
//import robot.Robot;

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
    private static final int TURN_LENGTH = 10;

    /**
     * Constructor: creates the arena, then adds the robots to the arena
     * 
     */
    public Arena()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        Builder builder = new Builder();
        ArrayList<Robot> robots = builder.getRobots();
        ArrayList<Location> spawnLocations = SpawnMap.getSpawnLocations(robots.size());
        
        for (Robot robot : robots)
        {
            //adds each robot to a random spawn location and removes that spawn location
            //this.add(robot, spawnLocations.remove((int) (Math.random() * spawnLocations.size())));
            
        }
        this.add(new Robot(), 200.0, 200.0);
        
        //this.addObject(new Obstacle(20, 100, 100), 300, 200);
        this.makeWalls();
        this.setActOrder(Robot.class, Projectile.class);
    }
    
    private void makeWalls()
    {
        Wall wall;
        
        wall = new Wall(WIDTH - Wall.THICKNESS * 2, Wall.THICKNESS);
        this.addObject(wall, WIDTH / 2, Wall.THICKNESS / 2);
        wall.setRotation(0);
        
        wall = new Wall(WIDTH - Wall.THICKNESS * 2, Wall.THICKNESS);
        this.addObject(wall, WIDTH / 2, HEIGHT - Wall.THICKNESS / 2);
        wall.setRotation(0);
        
        wall = new Wall(HEIGHT, Wall.THICKNESS);
        this.addObject(wall, WIDTH - Wall.THICKNESS / 2 - 1, HEIGHT / 2);
        wall.setRotation(90);
        
        wall = new Wall(HEIGHT, Wall.THICKNESS);
        this.addObject(wall, Wall.THICKNESS / 2 - 1, HEIGHT / 2);
        wall.setRotation(90);
    }
    
    /**
     * 
     */
    public void act()
    {
    }
    
    //Methods for adding/removing things to the arena
    
    /**
     * Returns an ArrayList of Locations at which a Robot may spawn
     * 
     * @param numLocations  the number of Locations required 
     * @return              the ArrayList of Locations
     *
    private ArrayList<Location> getSpawnLocations(int numLocations)
    {
        
        ArrayList<Location> spawnLocations = new ArrayList<Location>(numLocations);
        
        //make something that gives locations that look like the dots on dice as a function of the number
        //locations needed and the size of the arena, we have to decide
        //whether each robot has a unique image or not. For now, here's two temporary locations
        //also, are all robots going to start off facing the center or something? or random?
        //also, make sure the image doesnt hang off the screen, we need to do something about boundaries
        
        spawnLocations.add(new Location(50, 75));
        spawnLocations.add(new Location(100, 250));
        
        return spawnLocations;
    }
    
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
    
    public void add(ArenaActor actor, double x, double y)
    {
        this.addObject(actor, (int) (x + .5), (int) (x + .5));
        actor.setLocation(x, y);
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
    
    //Public getter methods
   /**
    * What are these for? -Brendan
    * Easily accessing ArrayLists of the different types of things so if there're class-specific actions
    * they can be taken without casting shenanigans -Haley
    public ArrayList<Obstacle> getObstacles()
    {
        return null;
    }
    
    public ArrayList<Projectile> getProjectiles()
    {
        return null;
    }
    
    public ArrayList<Robot> getRobots()
    {
        return null;
    }
    
    public ArrayList<ArenaActor> getArenaActors()
    {
        return this.getObjects(ArenaActor);
    }*/
    
    public int getTurnLength()
    {
        return this.TURN_LENGTH;
    }
    
    

    public static boolean xIsInBoundaries(int x)
    {
        return ((x >= 0) && (x <= Arena.WIDTH));
    }
   
    public static boolean yIsInBoundaries(int y)
    {
        return ((y >= 0) && (y <= Arena.WIDTH));
    }

    
    public static boolean isInBoundaries(int x, int y)
    {
        return (xIsInBoundaries(x) && yIsInBoundaries(y));
    }
}
