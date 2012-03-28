package emulator.cpu;

/**
 * Pinnable objects can be connected to one another for communication.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public abstract class Connector<E>
{   
    protected Connection<E> connection = new Connection(this);
    
    public Connector<E> connect(Connector<E> c)
    {
        this.connection.connect(c);
        return this;
    }
    public Connector<E> connect(Connection<E> c)
    {
        this.connection = c;
        return this;
    }
    
    public void disconnect()
    {
        this.connection = new Connection(this);
    }
    
    public E getValue()
    {
        return this.connection.get();
    }
    public void setValue(E e)
    {
        this.connection.set(e);
    }
    
    public Connection<E> getConnection()
    {
        return this.connection;
    }
}
