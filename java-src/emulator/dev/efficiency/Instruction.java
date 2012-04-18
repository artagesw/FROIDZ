package emulator.dev.efficiency;

public abstract class Instruction
{
    protected Processor proc;
    
    public abstract void execute();
}
