/**
 * Abstract class ToastyProcessor - Runs ToastyAssembly
 * 
 * @author Alex Teiche, Jacob Weiss
 * @version 0.0.1
 */
public class ToastyProcessor extends Processor
{
    // 16 Bit registers
    private char[] registers;
    
    // Data space
    public char[] sram;
    
    // I/O Space
    public char[] io;
    
    // Program space
    public int[] flash;
    
    private int programCounter = 0;
    
    public ToastyProcessor(int regNum, int sramNum, int ioNum, int flashNum, int clockSpeed)
    {
        super(clockSpeed);
        
        this.registers = new char[regNum];
        this.sram = new char[sramNum];
        this.io = new char[ioNum];
        this.flash = new int[flashNum];
    }
    
    public ToastyProcessor()
    {
        // 256 Registers
        // 1M of Data-space(SRAM)
        // 1M of IO Space
        this(256, 65536, 65536, 1, 1);
    } 
    
    /**
     * 
     */
    public int execute()
    {
        // fetch
        int instr = this.flash[this.programCounter];
        int opCode = (instr & 0xFF000000) >> 24;
        
        // execute
        switch (opCode)
        {
            case TC.ADD: // Add
            {
                char Rb = (char)(instr & 0xFF);
                char Ra = (char)((instr = instr >> 8) & 0xFF);
                char Rd = (char)((instr = instr >> 8) & 0xFF);
            
                int res = this.sram[Rb] + this.sram[Ra];

                if (res > 65535)
                {
                    // Set C flag in SREG
                    this.io[TC.SREG] |= 1;
                }
                
                this.sram[Rd] = (char)((this.sram[Rb] + this.sram[Ra]) % 65536);
                
                this.programCounter++;
                
                break;
            }
                
            case TC.LDI: // LDI
            {
                int K = instr & 0xFFFF;
                char Rd = (char)((instr = instr >> 16) & 0xFF);
                
                this.sram[Rd] = (char)K;
            
                this.programCounter++;
                
                break;
            }
            
            case TC.EOF:
            {
                this.programCounter = 0;
                
                break;
            }
           
            
            default:
            {
                System.out.println(opCode);
                System.out.println("Illegal Instruction");
                break;
            }
        }

        

        
        return 1;   
    }
}
