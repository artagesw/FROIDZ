package emulator.cpu; 
 
import java.util.ArrayList;

/**
 * Abstract class CommunicationMaster
 * 
 * @author Alex Teiche, Jacob Weiss
 * @version 0.0.1
 */
public abstract class CommunicationMaster extends Communication
{
    private byte[] registers;
    
    private ArrayList<CommunicationSlave> devices = new ArrayList(256);
}
