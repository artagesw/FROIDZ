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
    
    // Peripherals
    private Peripheral[] peripherals;
    
    // Program space
    public int[] flash;
    
    private int programCounter = 0;
    
    public ToastyProcessor(int regNum, int sramNum, int ioNum, int flashNum, int clockSpeed, Peripheral... peripherals)
    {
        super(clockSpeed);
        
        this.registers = new char[regNum];
        this.sram = new char[sramNum];
        this.io = new char[ioNum];
        this.flash = new int[flashNum];
        this.peripherals = peripherals;
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
            case Opcodes.ADD: // Add
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
            case Opcodes.ADDI:
            {
            }
            case Opcodes.ADDb:
            {
            }
            case Opcodes.ADC:
            {
            }
            case Opcodes.ADCI:
            {
            }
            case Opcodes.ADCb:
            {
            }
            case Opcodes.SUB:
            {
            }
            case Opcodes.SUBI:
            {
            }
            case Opcodes.SUBb:
            {
            }
            case Opcodes.SBC:
            {
            }
            case Opcodes.SBCI:
            {
            }
            case Opcodes.SBCb:
            {
            }            
            case Opcodes.MUL:
            {
            }
            case Opcodes.MULI:
            {
            }
            case Opcodes.MULb:
            {
            }
            case Opcodes.MULS:
            {
            }
            case Opcodes.MULSI:
            {
            }
            case Opcodes.MULSb:
            {
            }
            case Opcodes.MULSU:
            {
            }
            case Opcodes.MULSUI:
            {
            }
            case Opcodes.MULSUb:
            {
            }
            case Opcodes.MULUS:
            {
            }
            case Opcodes.MULUSI:
            {
            }
            case Opcodes.INC:
            {
            }
            case Opcodes.DEC:
            {
            }
            case Opcodes.NEG:
            {
            }
            case Opcodes.ASR:
            {
            }
            case Opcodes.ASRb:
            {
            }
            case Opcodes.AND:
            {
                char Rb = (char)(instr & 0xFF);
                char Ra = (char)((instr = instr >> 8) & 0xFF);
                char Rd = (char)((instr = instr >> 8) & 0xFF);
                
                this.registers[Rd] = this.registers[Ra] & this.registers[Rb];
            }
            case Opcodes.ANDI:
            {
            }
            case Opcodes.OR:
            {
            }
            case Opcodes.ORI:
            {
            }
            case Opcodes.XOR:
            {
            }
            case Opcodes.XORI:
            {
            }
            case Opcodes.NOT:
            {
            }
            case Opcodes.NOTI:
            {
            }
            case Opcodes.CBIO:
            {
            }
            case Opcodes.SBIO:
            {
            }
            case Opcodes.LSL:
            {
            }
            case Opcodes.LSLb:
            {
            }
            case Opcodes.LSR:
            {
            }
            case Opcodes.LSRb:
            {
            }
            case Opcodes.OUT:
            {
            }
            case Opcodes.IN:
            {
            }
            case Opcodes.LDI:
            {
                int K = instr & 0xFFFF;
                char Rd = (char)((instr = instr >> 16) & 0xFF);
                
                this.sram[Rd] = (char)K;
            
                this.programCounter++;
                
                break;
            }
            case Opcodes.LD:
            {
            }
            case Opcodes.STS:
            {
            }
            case Opcodes.ST:
            {
            }
            case Opcodes.MOV:
            {
            }
            case Opcodes.CP:
            {
            }
            case Opcodes.CPI:
            {
            }
            case Opcodes.JMP:
            {
            }
            case Opcodes.RJMP:
            {
            }
            case Opcodes.IJMP:
            {
            }
            case Opcodes.BRBS:
            {
            }
            case Opcodes.BRBC:
            {
            }
            case Opcodes.NOP:
            {
            }
            case Opcodes.EOF:
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

        for (Peripheral p : this.peripherals)
        {
            p.clock();
        }
        
        return 1;   
    }
}
