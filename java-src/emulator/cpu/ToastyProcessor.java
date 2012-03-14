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
            case OPCODES.ADD: // Add
            {
                char Rb = (char)(instr & 0xFF);
                char Ra = (char)((instr = instr >> 8) & 0xFF);
                char Rd = (char)((instr = instr >> 8) & 0xFF);
            
                int res = this.sram[Rb] + this.sram[Ra];

                if (res > 65535)
                {
                    // Set C flag in SREG
                    this.io[IO.SREG] |= SREG.C;
                }
                
                this.sram[Rd] = (char)((this.sram[Rb] + this.sram[Ra]) % 65536);
                
                this.programCounter++;
                
                break;
            }
            case OPCODES.ADDI:
            {
            }
            case OPCODES.ADDb:
            {
            }
            case OPCODES.ADC:
            {
            }
            case OPCODES.ADCI:
            {
            }
            case OPCODES.ADCb:
            {
            }
            case OPCODES.SUB:
            {
            }
            case OPCODES.SUBI:
            {
            }
            case OPCODES.SUBb:
            {
            }
            case OPCODES.SBC:
            {
            }
            case OPCODES.SBCI:
            {
            }
            case OPCODES.SBCb:
            {
            }            
            case OPCODES.MUL:
            {
            }
            case OPCODES.MULI:
            {
            }
            case OPCODES.MULb:
            {
            }
            case OPCODES.MULS:
            {
            }
            case OPCODES.MULSI:
            {
            }
            case OPCODES.MULSb:
            {
            }
            case OPCODES.MULSU:
            {
            }
            case OPCODES.MULSUI:
            {
            }
            case OPCODES.MULSUb:
            {
            }
            case OPCODES.MULUS:
            {
            }
            case OPCODES.MULUSI:
            {
            }
            case OPCODES.INC:
            {
            }
            case OPCODES.DEC:
            {
            }
            case OPCODES.NEG:
            {
            }
            case OPCODES.ASR:
            {
            }
            case OPCODES.ASRb:
            {
            }
            case OPCODES.AND:
            {
                char Rb = (char)(instr & 0xFF);
                char Ra = (char)((instr = instr >> 8) & 0xFF);
                char Rd = (char)((instr = instr >> 8) & 0xFF);
                
                this.registers[Rd] = (char)(this.registers[Ra] & this.registers[Rb]);
                
                // SREG Effects
                // S             
                char sreg = this.io[IO.SREG];
                sreg &= ~SREG.V & ~SREG.S & ~SREG.N;
                //this.io[IO.SREG] =;
                //(Rd >> 7)
            }
            case OPCODES.ANDI:
            {
            }
            case OPCODES.OR:
            {
            }
            case OPCODES.ORI:
            {
            }
            case OPCODES.XOR:
            {
            }
            case OPCODES.XORI:
            {
            }
            case OPCODES.NOT:
            {
            }
            case OPCODES.NOTI:
            {
            }
            case OPCODES.CBIO:
            {
            }
            case OPCODES.SBIO:
            {
            }
            case OPCODES.LSL:
            {
            }
            case OPCODES.LSLb:
            {
            }
            case OPCODES.LSR:
            {
            }
            case OPCODES.LSRb:
            {
            }
            case OPCODES.OUT:
            {
            }
            case OPCODES.IN:
            {
            }
            case OPCODES.LDI:
            {
                int K = instr & 0xFFFF;
                char Rd = (char)((instr = instr >> 16) & 0xFF);
                
                this.sram[Rd] = (char)K;
            
                this.programCounter++;
                
                break;
            }
            case OPCODES.LD:
            {
            }
            case OPCODES.STS:
            {
            }
            case OPCODES.ST:
            {
            }
            case OPCODES.MOV:
            {
            }
            case OPCODES.CP:
            {
            }
            case OPCODES.CPI:
            {
            }
            case OPCODES.JMP:
            {
            }
            case OPCODES.RJMP:
            {
            }
            case OPCODES.IJMP:
            {
            }
            case OPCODES.BRBS:
            {
            }
            case OPCODES.BRBC:
            {
            }
            case OPCODES.NOP:
            {
            }
            case OPCODES.EOF:
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
