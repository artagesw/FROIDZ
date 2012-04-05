package robot;

import java.util.Scanner;
import emulator.cpu.IAsynchronousUSART;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Runnable;
/**
 * Write a description of class Printer here.
 * 
 * @author Jacob Weiss
 * @version 0.1.0
 */
public class ConsolePart extends Part implements Runnable, IAsynchronousUSART
{
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    public ConsolePart()
    {
        Thread thread = new Thread(this, "Printer");
        thread.start();
    }
    
    public void run()
    {
        while (true)
        {
            try
            {
                if (this.input.ready())
                {
                    synchronized (this.device)
                    {
                        for (char c : input.readLine().toCharArray())
                        {
                            this.device.Rx((byte)c);
                        }
                    }
                }
            }
            catch (java.io.IOException e)
            {
                System.out.println("IOException");
            }
        }
    }
    
    public void Rx(byte data)
    {
        System.out.print((char)data);
    }
}
