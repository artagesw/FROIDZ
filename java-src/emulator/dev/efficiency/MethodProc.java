public class MethodProc extends Processor
{
    public Instruction instr = new Increment(this);
    
    public void execute()
    {
        this.instr.execute();
    }
}
