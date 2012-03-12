import java.util.ArrayList;

/**
 * Abstract class CommunicationMaster - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class CommunicationMaster implements CommunicationInterface
{
    private byte[] registers;
    
    private ArrayList<CommunicationSlave> devices = new ArrayList(255);
}
