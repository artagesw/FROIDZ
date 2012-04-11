 
import java.util.ArrayList;

/**
 * Write a description of class CircularStack here.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class CircularStack
{
    private ArrayList<Vector> stack;
    private int pointer;
    private int bottom;
    
    public CircularStack(int size)
    {
        this.stack = new ArrayList(size);
        for (int i = 0; i < size; i++)
        {
            this.stack.add(new Vector());
        }
        this.pointer = 0;
        this.bottom = size - 1;
    }
    
    public void push(Vector element)
    {
        this.stack.get(this.pointer).copy(element);
        this.pointer = (this.pointer + 1) % this.stack.size();
        if (this.pointer == this.bottom)
        {
            this.bottom = (this.bottom + 1) % this.stack.size();
        }
    }
    
    public Vector pop()
    {
        if (this.pointer == (this.bottom + 1) % this.stack.size())
        {
            return null;
        }
        this.pointer = ((this.pointer - 1) + this.stack.size()) % this.stack.size();
        return this.stack.get(this.pointer);
    }
}
