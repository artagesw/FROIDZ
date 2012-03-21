package cpu;

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
       this.value = initialValue;
    }
}
