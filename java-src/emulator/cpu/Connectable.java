package emulator.cpu;

/**
 * Pinnable objects can be connected to one another for communication.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public abstract class Connectable<E>
{   
    protected Connection<E> connection = new Connection(this);
    
    public void connect(Connectable<E> c)
    {
        this.connection.connect(c);
    }
    public void connect(Connection<E> c)
    {
        this.connection = c;
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
