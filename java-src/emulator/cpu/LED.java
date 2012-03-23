package cpu;

public class LED extends Connectable<Boolean>
{
    public void act()
    {
        if (this.getValue())
        {
            System.out.print("on ");
        }
        else
        {
            System.out.print("off ");
        }
    }
}
