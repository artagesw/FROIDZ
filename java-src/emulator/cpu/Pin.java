package emulator.cpu;

/** 
 * One pin of a microcontroller.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class Pin<E> extends Connector<E>
{
    public Pin()
    {
        this.setValue(null);
    }
    public Pin(E initial)
    {
        this.setValue(initial);
    }
}
