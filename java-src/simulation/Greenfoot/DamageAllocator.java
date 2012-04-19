package simulation.Greenfoot;

import java.util.ArrayList;

/**
 * Method to allocate damage to a random part of the robot
 * 
 * @author Haley B-E
 * @version 0.0.1
 */
public class DamageAllocator
{
    
    public ArrayList<String> parts = new ArrayList<String>();
    
    public ArrayList<Integer> weights = new ArrayList<Integer>();
    
    /**
     * Deals damage to a random part
     *          Assumes robot has a list of parts with a corresponding list of 'weights' - each part's chance of taking damage
     *          Finds total 'weight', then deals damage to the part p for which a random number n is within the range
     *          weight(p-1) < n <= weight(p)
     * @param damage        amount of damage to deal to the part
     */
    public void takeDamage(int damage)
    {
        int totalWeight = 0;

        for (int i : weights)
        {
            totalWeight += i;
        }
        
        double toDamage = (Math.random() * totalWeight);

        int checked = 0;
        //checks to see if damage is between range of previously-checked value and next boundary in the list
        for (int i = 0; i < weights.size(); i++)
        {
            if ((toDamage >= checked) && (toDamage < (weights.get(i) + checked)))
            {
                System.out.println(parts.get(i));   //this is the part that will have damage done to it
                return;
            }
            else
            {
                checked += weights.get(i);
            }
        }        
    }
}
