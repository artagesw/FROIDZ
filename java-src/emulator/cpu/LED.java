package cpu;

public class LED extends Pinnable<Boolean>
{
    public void act()
    {
        for (Pinnable<Boolean> b : this.connections)
        {
            if (b.getValue())
            {
                System.out.print("on ");
            }
            else
            {
                System.out.print("off ");
            }
        }
        System.out.println();
    }
}
