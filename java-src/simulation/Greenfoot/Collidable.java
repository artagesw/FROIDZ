 

 

/**
 * Write a description of class Collidable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Collidable  
{
    public int getDeflectionAngle();
    public void setDeflectionRotation(int angle);
    public void smallMove(double distance);
    public int getRotation();
}
