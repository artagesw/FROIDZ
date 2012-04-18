package emulator.dev.efficiency;

import java.util.Date;

public class Main
{
    public static void Main(long cycles)
    {
        Processor s = new SwitchProc();
        Processor m = new MethodProc();
        
        long switchTime = 0;
        long methodTime = 0;
        
        Date time = new Date();       
        switchTime = time.getTime();
        System.out.println(time.getTime());
        
        while (s.num < cycles)
        {
            s.execute();
        }
        time = new Date();
        switchTime -= (methodTime = time.getTime());
        System.out.println(time.getTime());
        
        while (m.num < cycles)
        {
            m.execute();
        }
        
        time = new Date();
        methodTime -= time.getTime();
        System.out.println(time.getTime());
        
        System.out.println("Switch: " + switchTime);
        System.out.println("Method: " + methodTime);
    }
}
