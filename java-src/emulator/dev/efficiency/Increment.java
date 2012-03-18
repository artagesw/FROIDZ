public class Increment extends Instruction
{
    public Increment(MethodProc proc)
    {
        this.proc = proc;
    }
    
    public void execute()
    {
        this.proc.num++;
    }
}
