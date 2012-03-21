package cpu;

import java.util.ArrayList;
/**
 * Pinnable objects can be connected to one another for communication.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public abstract class Pinnable<E>
{   
    protected E value;
    protected ArrayList<Pinnable<E>> connections = new ArrayList();
    
    public void connect(Pinnable<E> connection)
    {
        this.connections.add(connection);
        connection.connections.add(this);
    }
    
    public void disconnect(Pinnable<E> connection)
    {
        this.connections.remove(connection);
        connection.connections.remove(this);
    }
    
    public E getValue()
    {
        return this.value;
    }
    public void setValue(E e)
    {
        this.value = e;
    }
}
