import java.util.ArrayList;

/**
 * Spawn locations for given numbers of robots
 * 
 * @author Brendan Redmond 
 * @version 0.0.0
 */
public class SpawnMap  
{
    //the array of ArrayLists of spawn locations for a given number of robots
    private static Location[] locations = 
    {
        //top left
        new Location(Arena.MAX_ROBOT_WIDTH / 2 + Wall.THICKNESS / 2, Arena.MAX_ROBOT_HEIGHT / 2 + Wall.THICKNESS / 2),
        //bottom right
        new Location(Arena.WIDTH - Arena.MAX_ROBOT_WIDTH / 2 - Wall.THICKNESS / 2,
                     Arena.HEIGHT - Arena.MAX_ROBOT_HEIGHT / 2 - Wall.THICKNESS / 2),
        //bottom left
        new Location(Arena.MAX_ROBOT_WIDTH / 2 + Wall.THICKNESS / 2, Arena.HEIGHT - Arena.MAX_ROBOT_HEIGHT / 2 - Wall.THICKNESS / 2),
        //top right
        new Location(Arena.WIDTH - Arena.MAX_ROBOT_WIDTH / 2 - Wall.THICKNESS / 2, Arena.MAX_ROBOT_HEIGHT / 2 + Wall.THICKNESS / 2),
        //top middle
        new Location(Arena.WIDTH / 2, Arena.MAX_ROBOT_HEIGHT / 2 + Wall.THICKNESS / 2),
        //left middle
        new Location(Arena.MAX_ROBOT_WIDTH / 2 + Wall.THICKNESS / 2, Arena.HEIGHT / 2),
        //right middle
        new Location(Arena.WIDTH - Arena.MAX_ROBOT_WIDTH / 2 - Wall.THICKNESS / 2, Arena.HEIGHT / 2),
        //bottom middle
        new Location(Arena.WIDTH / 2, Arena.HEIGHT - Arena.MAX_ROBOT_HEIGHT / 2 - Wall.THICKNESS / 2)
    };
    
    
    public static ArrayList<Location> getSpawnLocations(int numLocations)
    {
        assert(numLocations <= locations.length);
        
        ArrayList<Location> spawnLocations = new ArrayList<Location>();
        
        for (int i = 0; i < numLocations; i++)
        {
            spawnLocations.add(locations[i]);
        }
        
        return spawnLocations;
    }
}
