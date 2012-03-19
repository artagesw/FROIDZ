import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import robot.Robot;

/**
 * A battle arena.
 * 
 * @author Brendan Redmond and Haley B-E
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
        
        this.setActOrder(Robot.class, Projectile.class);
    }
    
    /**
     * 
     */
    public void act()
    {
    }
    
    /**
     * The main game loop
     */
    public void play()
    {
        ArrayList<ArenaActor> actQueue = this.orderActors(this.getArenaActors());
        for (int i = 0; i < actQueue.size(); i++)
        {
            actQueue.get(i).act();
            this.resolveCollisions();
        }
    }
    
    
    /**
     * Places actors in an arraylist in order of speed - greatest speed first, least speed last
     * @param toOrder       ArrayList of actors to be ordered by speed
     * @return              ArrayList of given actors, ordered by speed
     */
    private ArrayList<ArenaActor> orderActors(ArrayList<ArenaActor> toOrder)
    {
        ArrayList<ArenaActor> ordered = new ArrayList<ArenaActor>();
        for (ArenaActor actor : actors)
        {
            ordered.add(actor, findInsert(actor, ordered));
        }
        return ordered;
    }
    
    /**
     * NOTE: THIS CAN PROBABLY BE IMPROVED IN TERMS OF EFFICIENCY
     * Helper method for orderActors --> finds the correct location for the actor in the arraylist based on its speed
     *          (list sorted from greatest speed to least)
     * @param actor             the actor to find a place for
     * @param toPlaceIn         the list to find a place to put the actor into
     * @return                  the index at which the actor should be placed
     */
    private int findInsert(ArenaActor actor, ArrayList<ArenaActor> toPlaceIn)
    {
        int i = actor.getSpeed();
        int j = 0;
        while ((toPlaceIn.get(j).getSpeed() > i) && (j < toPlaceIn.getSize() - 1))
        {
            j++;
        }
        return j;
    }
    
    private void resolveCollisions()
    {
        for (Robot robot : this.getRobots())
        {
            ArrayList<ArenaActor> collisions = robot.getIntersectingObjects(ArenaActor);
            if (collisions != null)
            {
                for (ArenaActor actor : collisions)
                {
                    robot.takeDamage(actor.doDamage);
                    //include a way to get rid of things like bullets that disappear upon impact
                }
            }
        }
        for (Projectile projectile : this.getProjectiles())
        {
            if ((projectile.getIntersectingObjects(Boundary) != null) && (projectile.getIntersectingObjects(Obstacle) != null))
            {
                projectile.removeSelfFromGrid(); //include a way to make it explode instead at some point
            }
        }
    }
    
    
    //Methods for adding/removing things to the arena
    
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
    
    /**
     * Adds an ArenaActor to the given location
     * 
     * @param toAdd         the ArenaActor to place in the arena
     * @param xCoord        x coordinate of the desired location
     * @param
     */
    public boolean addArenaActor(ArenaActor toAdd, int xCoord, int yCoord)
    {
        if (this.getObjectsAt(xCoord, yCoord, null) != null)
        {
            return false;
        }     
        this.addObject(toAdd, xCoord, yCoord);
        return true;
    }
    
    /**
     * Removes given ArenaActor from arena
     * 
     * @param toRemove      ArenaActor to remove from arena
     */
    public void removeArenaActor(ArenaActor toRemove)
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
    
    public ArrayList<ArenaActor> getObstacles()
    {
        return null;
    }
    
    public ArrayList<ArenaActor> getProjectiles()
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
    }
}
