/**
 * Abstract class ToastyProcessor - Runs ToastyAssembly
 * 
 * @author Alex Teiche, Jacob Weiss
 * @version 0.0.1
 */
public class ToastyProcessor extends Processor
{
    public Memory mem;
    
    // Program space
    public int[] flash;
    
    // Peripherals
    private Peripheral[] peripherals;
    
    private int programCounter = 0;
    
    public ToastyProcessor(Memory mem, int flashNum, int clockSpeed, Peripheral... peripherals)
    {
        super(clockSpeed);
        
        this.mem = mem;

        this.flash = new int[flashNum];
        this.peripherals = peripherals;
    }
    
    /**
     * 
     */
    public int execute()
    {
        // fetch
        int instr = this.flash[this.programCounter];
        int opCode = (instr & 0xFF000000) >> 24;
        int clockCount = 0;
        
        // execute
        switch (opCode)
        {
            case OPCODES.ADD_b:
            {
                char Rb = (char)(instr & 0xFF);
                char Ra = (char)((instr = instr >> 8) & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);    
                
                char Vb = (char)(this.mem.registers[Rb] & 0xFF);
                char Va = (char)(this.mem.registers[Ra] & 0xFF);
                
                char result = (char)(Va + Vb);
                
                char sreg = this.mem.io[IO.SREG];
                if (result > 255) // Set the carry bit
                {
                    sreg |= SREG.C;
                }
                else
                {
                    sreg &= ~SREG.C;
                }
                if (result == 0) // Set the 0 bit
                {
                    sreg |= SREG.Z;
                }
                else
                {
                    sreg &= ~SREG.Z;
                }
                if ((result & 0x7F) != 0) // Set the two's complement bit
                {
                    sreg |= SREG.N;
                }
                else
                {
                    sreg &= ~SREG.N;
                }
                if (((Vb & Va & ~result | ~Vb & ~Va & result) & 0x80) != 0) // Set the two's complement overflow bit
                {
                    sreg |= SREG.V;
                }
                else
                {
                    sreg &= ~SREG.V;
                }
                if (((Vb & Va | Va & ~Vb | ~result & Vb) & 0x01) != 0) // Set the half-carry bit
                {
                    sreg |= SREG.H;
                }
                else
                {
                    sreg &= ~SREG.H;
                }
                if (((sreg & SREG.N) != 0) ^ ((sreg & SREG.V) != 0))
                {
                    sreg |= SREG.S;
                }
                else
                {
                    sreg &= ~SREG.S;
                }
                
                this.mem.io[IO.SREG] = sreg;                
                this.mem.registers[Rd] = (char)(result & 0xFF);
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            case OPCODES.ADDI_b:
            {
                break;
            }
            case OPCODES.ADC_b:
            {
                break;
            }
            case OPCODES.ADCI_b:
            {
                break;
            }
            case OPCODES.SUB_b:
            {
                break;
            }
            case OPCODES.SUBI_b:
            {
                break;
            }
            case OPCODES.SBC_b:
            {
                break;
            }
            case OPCODES.SBCI_b:
            {
                break;
            }
            case OPCODES.MUL_b:
            {
                break;
            }
            case OPCODES.MULI_b:
            {
                break;
            }
            case OPCODES.MULS_b:
            {
                break;
            }
            case OPCODES.MULSI_b:
            {
                break;
            }
            case OPCODES.MULSU_b:
            {
                break;
            }
            case OPCODES.MULSUI_b:
            {
                break;
            }
            case OPCODES.MULUS_b:
            {
                break;
            }
            case OPCODES.MULUSI_b:
            {
                break;
            }
            case OPCODES.INC_b:
            {
                break;
            }
            case OPCODES.DEC_b:
            {
                break;
            }
            case OPCODES.NEG_b:
            {
                break;
            }
            case OPCODES.ASR_b:
            {
                break;
            }
            case OPCODES.AND_b:
            {
                byte Rb = (byte)(instr & 0xFF);
                byte Ra = (byte)((instr = instr >> 8) & 0xFF);
                byte Rd = (byte)((instr >> 8) & 0xFF);
                
                byte Vb = (byte)(this.mem.registers[Rb] & 0xFF);
                byte Va = (byte)(this.mem.registers[Ra] & 0xFF);
                
                byte result = (byte)((Va & Vb) & 0xFF);
                
                char sreg = this.mem.io[IO.SREG];
                sreg &= ~SREG.V;
                if (result == 0)
                {
                    sreg |= SREG.Z;
                }
                else
                {
                    sreg &= ~SREG.Z;
                }
                if ((result & 0x7F) != 0)
                {
                    sreg |= SREG.N;
                }
                else
                {
                    sreg &= ~SREG.N;
                }
                if (((sreg & SREG.N) != 0) ^ ((sreg & SREG.V) != 0))
                {
                    sreg |= SREG.S;
                }
                else
                {
                    sreg &= ~SREG.S;
                }
                
                this.mem.io[IO.SREG] = sreg;
                this.mem.registers[Rd] = (char)result;
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            case OPCODES.ANDI_b:
            {
                break;
            }
            case OPCODES.OR_b:
            {
                byte Rb = (byte)(instr & 0xFF);
                byte Ra = (byte)((instr = instr >> 8) & 0xFF);
                byte Rd = (byte)((instr >> 8) & 0xFF);
                
                byte Vb = (byte)(this.mem.registers[Rb] & 0xFF);
                byte Va = (byte)(this.mem.registers[Ra] & 0xFF);
                
                byte result = (byte)((Va | Vb) & 0xFF);
                
                char sreg = this.mem.io[IO.SREG];
                sreg &= ~SREG.V;
                if (result == 0)
                {
                    sreg |= SREG.Z;
                }
                else
                {
                    sreg &= ~SREG.Z;
                }
                if ((result & 0x7F) != 0)
                {
                    sreg |= SREG.N;
                }
                else
                {
                    sreg &= ~SREG.N;
                }
                if (((sreg & SREG.N) != 0) ^ ((sreg & SREG.V) != 0))
                {
                    sreg |= SREG.S;
                }
                else
                {
                    sreg &= ~SREG.S;
                }
                
                this.mem.io[IO.SREG] = sreg;
                this.mem.registers[Rd] = (char)result;
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            case OPCODES.ORI_b:
            {
                break;
            }
            case OPCODES.XOR_b:
            {
                byte Rb = (byte)(instr & 0xFF);
                byte Ra = (byte)((instr = instr >> 8) & 0xFF);
                byte Rd = (byte)((instr >> 8) & 0xFF);
                
                byte Vb = (byte)(this.mem.registers[Rb] & 0xFF);
                byte Va = (byte)(this.mem.registers[Ra] & 0xFF);
                
                byte result = (byte)((Va ^ Vb) & 0xFF);
                
                char sreg = this.mem.io[IO.SREG];
                sreg &= ~SREG.V;
                if (result == 0)
                {
                    sreg |= SREG.Z;
                }
                else
                {
                    sreg &= ~SREG.Z;
                }
                if ((result & 0x7F) != 0)
                {
                    sreg |= SREG.N;
                }
                else
                {
                    sreg &= ~SREG.N;
                }
                if (((sreg & SREG.N) != 0) ^ ((sreg & SREG.V) != 0))
                {
                    sreg |= SREG.S;
                }
                else
                {
                    sreg &= ~SREG.S;
                }
                
                this.mem.io[IO.SREG] = sreg;
                this.mem.registers[Rd] = (char)result;
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            case OPCODES.XORI_b:
            {
                break;
            }
            case OPCODES.NOT_b:
            {
                break;
            }
            case OPCODES.NOTI_b:
            {
                break;
            }
            case OPCODES.CBIO_b:
            {
                break;
            }
            case OPCODES.SBIO_b:
            {
                break;
            }
            case OPCODES.LSL_b:
            {
                break;
            }
            case OPCODES.LSR_b:
            {
                break;
            }
            case OPCODES.OUT_b:
            {
                char Rr = (char)(instr & 0xFF);
                char A = (char)((instr >> 8) & 0xFF);
                
                this.mem.writeIO(A, (char)(this.mem.registers[Rr] & 0xFF));
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            case OPCODES.IN_b:
            {
                char A = (char)(instr & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);
                
                this.mem.registers[Rd] = (char)(this.mem.readIO(A) & 0xFF);
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            case OPCODES.LDI_b:
            {
                char K = (char)(instr & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);
                
                this.mem.registers[Rd] = K;
            
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            case OPCODES.LD_b:
            {
                char I = (char)(instr & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);
                
                char addr = (char)(((this.mem.registers[I] & 0xFF) << 8) & (this.mem.registers[I + 1] & 0xFF));
                
                this.mem.registers[Rd] = this.mem.sram[addr];
                
                this.programCounter++;
                
                clockCount = 2; // Not perfectly accurate. See instruction manual.
                
                break;
            }
            case OPCODES.STS_b:
            {
                char Rr = (char)(instr & 0xFF);
                char k = (char)((instr >> 8) & 0xFFFF);
                
                this.mem.write(k, this.mem.registers[Rr]);
                
                this.programCounter++;
                
                clockCount = 2;
                
                break;
            }
            case OPCODES.ST_b:
            {
                char Rr = (char)(instr & 0xFF);
                char I = (char)((instr >> 8) & 0xFF);
                
                char addr = (char)((this.mem.registers[I] & 0xFF) & ((this.mem.registers[I + 1] & 0xFF) << 8));
                
                this.mem.write(addr, this.mem.registers[Rr]);
                
                this.programCounter++;
                
                clockCount = 2; // Not perfectly accurate. See instruction manual.
                
                break;
            }
            case OPCODES.MOV_b:
            {
                break;
            }
            case OPCODES.CP_b:
            {
                break;
            }
            case OPCODES.CPI_b:
            {
                break;
            }
            case OPCODES.JMP_b:
            {
                break;
            }
            case OPCODES.RJMP_b:
            {
                break;
            }
            case OPCODES.IJMP_b:
            {
                break;
            }
            case OPCODES.BRBS_b:
            {
                break;
            }
            case OPCODES.BRBC_b:
            {
                break;
            }
            case OPCODES.NOP:
            {
                clockCount = 1;
                
                this.programCounter += 1;
            }
            case OPCODES.EOF:
            {
                this.programCounter = 0;
                
                break;
            }  
            
            /*
            case OPCODES.ADD_w:
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
            case OPCODES.AND_w:
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
            case OPCODES.LDI_w:
            {
                int K = instr & 0xFFFF;
                char Rd = (char)((instr = instr >> 16) & 0xFF);
                
                this.sram[Rd] = (char)K;
            
                this.programCounter++;
                
                break;
            }            
            */
           
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
        
        return clockCount;   
    }
}
