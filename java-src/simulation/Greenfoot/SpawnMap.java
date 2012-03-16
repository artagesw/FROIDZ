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
        //none of this is right, fix
        new Location(Arena.MAX_ROBOT_WIDTH / 2, ARENA.MAX_ROBOT_HEIGHT / 2),
        new Location(Arena.MAX_ROBOT_WIDTH - Arena.MAX_ROBOT_WIDTH / 2,
                        ARENA.HEIGHT - ARENA.MAX_ROBOT_HEIGHT / 2),
        new Location(Arena.MAX_ROBOT_WIDTH / 2, ARENA.MAX_ROBOT_HEIGHT - ARENA.MAX_ROBOT_HEIGHT / 2),
        new Location(Arena.MAX_ROBOT_WIDTH - Arena.MAX_ROBOT_WIDTH / 2,
                        ARENA.MAX_ROBOT_HEIGHT - ARENA.MAX_ROBOT_HEIGHT / 2)
    };
    
    
    public static ArrayList<Location> getSpawnLocations(int numLocations)
    {
        ArrayList<Location> spawnLocations = new ArrayList<Location>();
        
        for (int i = 0; i < numLocations; i++)
        {
            spawnLocations.add(locations[i]);
        }
        
        return spawnLocations;
    }
}
