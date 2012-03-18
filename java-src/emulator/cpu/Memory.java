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
    
    public void writeIO(int addr, char data)
    {        
        this.io[addr] = data;
    }
    public char readIO(int addr)
    {
        return this.io[addr];
    }
    
    public void write(int addr, char data)
    {
        if (addr < this.registers.length)
        {
            this.registers[addr] = data;
        }
        else if (addr < this.io.length)
        {
            this.writeIO(addr, data);
        }
        else if (addr < this.sram.length)
        {
            this.sram[addr] = data;
        }
    }
    public char read(int addr)
    {
        if (addr < this.registers.length)
        {
            return this.registers[addr];
        }
        else if (addr < this.io.length)
        {
            return this.readIO(addr);
        }
        else if (addr < this.sram.length)
        {
            return this.sram[addr];
        }
        throw new java.lang.IndexOutOfBoundsException();
    }
}
