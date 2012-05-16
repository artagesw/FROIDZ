import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * A Projectile that doesn't bounce
 * 
 * @author Haley B-E 
 * @version 0.0.1
 */
public class BulletProjectile extends Projectile
{
    public BulletProjectile(Vector velocity, double mass, double radius, double x, double y)
    {
        super(velocity, mass, radius, x, y);
        this.getImage().setColor(Color.ORANGE);
    }
    
    /**
     * Resolves collisions between this Projectile and any intersecting Actors - including walls and obstacles
     */
    public void processCollision(Actor other)
    {
        if (other instanceof ArenaActor)
        {
            super.processCollision((ArenaActor)other);
        }
        else
        {
            ((Arena)this.getWorld()).removeObject(this);
        }
    }
}
