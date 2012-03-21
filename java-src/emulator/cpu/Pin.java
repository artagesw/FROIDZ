package CPU;

import java.util.ArrayList;
/**
 * One pin of a microcontroller.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class Pin<E> extends Pinnable<E>
{
    public Pin(E initialValue)
    {
       this.value = new Wrapper<E>(initialValue);
    }
    public void write(E value)
    {
        for (Wrapper<E> w : this.connections)
        {
            w.set(value);
        }
    }
    public E read()
    {
        return (E)this.connections.get(0).get();
    }
}
