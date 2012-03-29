package emulator.cpu;


/**
 * Write a description of class PWM here.
 * 
 * @author Jacob Weiss
 * @version 0.0.1
 */
public class PWM extends Peripheral
{
    private Pin<Byte> pin = new Pin();
    
    private int pwm;
    
    public PWM(Memory mem, int pwmRegister)
    {
        super(mem);
        this.pwm = pwmRegister;
    }
    
    public void connect(PinConnector p)
    {
        this.pin.connect(p.dataInPin());
    }
    
    public void clock()
    {
        this.pin.setValue((byte)this.mem.io[this.pwm]);
    }
}
