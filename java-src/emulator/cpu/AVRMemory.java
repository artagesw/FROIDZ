public class AVRMemory extends Memory
{
    /**
     * Constructor for objects of class AVRMemory
     */
    public AVRMemory()
    {
        super(32, 256, 4096);
    }
    
    public void writeIO(int addr, char data)
    {
        switch (addr)
        {
            case IO.UDR1:
            {
                this.io[IO.UCSR1A] &= ~32;
            }
        }
        super.writeIO(addr, data);
    }
    public char readIO(int addr)
    {
        switch (addr)
        {
            case IO.UDR1:
            {
                this.io[IO.UCSR1A] |= 32;
            }
        }
        return super.readIO(addr);
    }
}
