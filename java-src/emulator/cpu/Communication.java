
/**
 * Write a description of interface CommunicationSlave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Communication extends Peripheral
{
    public abstract void writeReg(int addr, int data);
    public abstract int readReg(int addr);
}
