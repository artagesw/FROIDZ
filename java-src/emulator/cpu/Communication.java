package cpu; 
 
/**
 * Communication
 * 
 * @author Jacob Weiss, Alex Teiche 
 * @version 0.0.1
 */
public abstract class Communication extends Peripheral
{
    public abstract void writeReg(int addr, int data);
    public abstract int readReg(int addr);
}
