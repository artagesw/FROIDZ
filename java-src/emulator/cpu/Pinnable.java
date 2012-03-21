package CPU;

import java.util.ArrayList;
/**
 * Pinnable objects can be connected to one another for communication.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public abstract class Pinnable<E>
{   
    protected Wrapper<E> value = new Wrapper();
    protected ArrayList<Wrapper<E>> connections = new ArrayList();
    
    public void connect(Pinnable<E> connection)
    {
        this.connections.add(connection.value);
        connection.connections.add(this.value);
    }
    
    public void disconnect(Pinnable<E> connection)
    {
        this.connections.remove(connection);
    }
}
