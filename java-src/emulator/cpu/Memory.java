/**
 * Memory
 * 
 * Represents the memory of a microcontroller device.
 */
public class Memory
{
    public char[] registers; // Register File
    public char[] io; // IO Space
    public char[] sram; // Data Space
    
    public Memory(int regNum, int ioNum, int sramNum)
    {
        this.registers = new char[regNum];
        this.io = new char[ioNum];
        this.sram = new char[sramNum];
    }
    
    public void write(int addr, char data)
    {
        try
        {
            this.registers[addr] = data;
        }
        catch (java.lang.IndexOutOfBoundsException e)
        {
            try
            {
                this.io[addr -= registers.length] = data;
            }
            catch (java.lang.IndexOutOfBoundsException f)
            {
                this.sram[addr - io.length] = data;
            }
        }
        return;
    }
    public char read(int addr)
    {
        try
        {
            return this.registers[addr];
        }
        catch (java.lang.IndexOutOfBoundsException e)
        {
            try
            {
                return this.io[addr -= registers.length];
            }
            catch (java.lang.IndexOutOfBoundsException f)
            {
                return this.sram[addr - io.length];
            }
        }
    }
}
