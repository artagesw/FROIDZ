package CPU;

/**
 * Write a description of class Wrapper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wrapper<E>
{
    private E element;
    
    public Wrapper()
    {
    }
    public Wrapper(E e)
    {
        this.element = e;
    }
    
    public void set(E e)
    {
        this.element = e;
    }
    
    public E get()
    {
        return this.element;
    }
}
