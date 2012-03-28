package emulator.cpu;

import java.util.HashSet;
/**
 * Write a description of class Wrapper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Connection<E>
{
    private E link;
    
    private HashSet<Connector<E>> connections = new HashSet();
    
    public Connection(Connector<E> c)
    {
        this.connections.add(c);
    }
    public Connection(E initial)
    {
        this.link = initial;
    }
    
    public void connect(Connector<E> c)
    {
        for (Connector<E> connection : c.getConnection().connections)
        {
            this.connections.add(connection);
            connection.connect(this);            
        }
    }
    
    public void set(E e)
    {
        this.link = e;
    }
    public E get()
    {
        return this.link;
    }
}
