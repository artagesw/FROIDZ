package emulator.cpu;

public class LED extends Connector<Boolean>
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
