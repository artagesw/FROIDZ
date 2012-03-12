
/**
 * Write a description of interface CommunicationSlave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface CommunicationInterface
{
    public void writeReg(int addr, int data);
    public int readReg(int addr);
    public void clock();
}
