package emulator.cpu;

import java.util.Scanner;
/**
 * Write a description of class Printer here.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class Printer implements IUSART, Runnable
{
    private Scanner input = new Scanner(System.in);
    private String outputBuffer = "";
    
    public Printer()
    {
        Thread thread = new Thread(this, "Printer");
        thread.start();
    }
    
    public void run()
    {
        try
        {
            while (true)
            {
                if (input.hasNextLine());
                {
                    this.outputBuffer += input.nextLine();
                }
                Thread.sleep(100);
            }
        }
        catch (java.lang.InterruptedException e)
        {
        }
    }
    
    public byte TxRx(byte b)
    {
        System.out.print((char)b);
        
        if (this.outputBuffer.length() == 0)
        {
            return 0;
        }
        char c = this.outputBuffer.charAt(0);
        this.outputBuffer = this.outputBuffer.substring(1);  
        
        return (byte)c;
    }
}
