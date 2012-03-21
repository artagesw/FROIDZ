package cpu;

public class LED extends Pinnable<Boolean>
{
    public void act()
    {
        if (this.value.get())
        {
            System.out.println("on");
        }
        else
        {
            System.out.println("off");
        }
    }
}
