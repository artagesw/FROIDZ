package emulator.cpu; 

import emulator.wp.*; 

/**
 * Abstract class ToastyProcessor - Runs ToastyAssembly
 * 
 * @author Alex Teiche, Jacob Weiss
 * @version 0.0.1
 */
public class ToastyProcessor extends Processor
{    
    public ToastyProcessor(Memory mem, int clockSpeed, Peripheral... peripherals)
    {
        super(clockSpeed);
        
        this.mem = mem;
    }
    
    /**
     * 
     */
    public int execute()
    {
        // fetch
        int instr = this.mem.flash[this.programCounter];
        int opCode = (instr & 0xFF000000) >> 24;
        int clockCount = 0;
        
        // execute
        switch (opCode)
        {
            /**
             * ADD.b
             * 
             * Perform byte addition on two registers and put the result into a third.
             * Ignores carry.
             */
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
                if ((result & 0x80) != 0) // Set the two's complement bit
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
                if (((Vb & Va | Va & ~result | ~result & Vb) & 0x04) != 0) // Set the half-carry bit
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
            /**
             * ADDI.b
             * 
             * Add a constant given 8-bit value to the given register.
             * Ignores carry.
             */
            case OPCODES.ADDI_b:
            {
                break;
            }
            /**
             * ADD.b
             * 
             * Perform byte addition on two registers and put the result into a third.
             * Uses carry.
             */
            case OPCODES.ADC_b:
            {
                break;
            }
            /**
             * ADDI.b
             * 
             * Add a constant given 8-bit value to the given register.
             * Ignores carry.
             */
            case OPCODES.ADCI_b:
            {
                break;
            }
            case OPCODES.SUB_b:
            {
                char Rb = (char)(instr & 0xFF);
                char Ra = (char)((instr = instr >> 8) & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);    
                
                char Vb = (char)(this.mem.registers[Rb] & 0xFF);
                char Va = (char)(this.mem.registers[Ra] & 0xFF);
                
                char result = (char)(Va - Vb);
                
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
                if ((result & 0x80) != 0) // Set the two's complement bit
                {
                    sreg |= SREG.N;
                }
                else
                {
                    sreg &= ~SREG.N;
                }
                if (((Vb & ~Va & ~result | ~Vb & Va & result) & 0x80) != 0) // Set the two's complement overflow bit
                {
                    sreg |= SREG.V;
                }
                else
                {
                    sreg &= ~SREG.V;
                }
                if (((~Vb & Va | Va & Vb | result & ~Vb) & 0x04) != 0) // Set the half-carry bit
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
            /**
             * AND.b
             * 
             * Performs an 8-bit binary and on two registers and puts
             * the result into a third.
             */
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
            /**
             * OR.b
             * 
             * Performs an 8-bit binary or on two registers and puts
             * the result into a third.
             */
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
            /**
             * XOR.b
             * 
             * Performs an 8-bit binary exclusive or on two registers and puts
             * the result into a third.
             */
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
            /**
             * OUT.b
             * 
             * Writes an 8-bit value from a general purpose register to
             * a given IO-Space address.
             */
            case OPCODES.OUT_b:
            {
                char Rr = (char)(instr & 0xFF);
                char A = (char)((instr >> 8) & 0xFF);
                
                this.mem.writeIO(A, (char)(this.mem.registers[Rr] & 0xFF));
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            /**
             * IN.b
             * 
             * Reads an 8-bit value from a a given IO-Space address
             * and puts it into a given general purpose register.
             * 
             */
            case OPCODES.IN_b:
            {
                char A = (char)(instr & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);
                
                this.mem.registers[Rd] = (char)(this.mem.readIO(A) & 0xFF);
                
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            /**
             * LDI.b
             * 
             * Loads a given 8-bit value to a general purpose register.
             */
            case OPCODES.LDI_b:
            {
                char K = (char)(instr & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);
                
                this.mem.registers[Rd] = K;
            
                this.programCounter++;
                
                clockCount = 1;
                
                break;
            }
            /**
             * LD.b
             * 
             * Loads an 8-bit value from the address pointer contained in a given register
             * to another given register.
             */
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
            /**
             * STS.b
             * 
             * Writes an 8-bit value contained in a given register to a given address in memory.
             */
            case OPCODES.STS_b:
            {
                char Rr = (char)(instr & 0xFF);
                char k = (char)((instr >> 8) & 0xFFFF);
                
                this.mem.write(k, this.mem.registers[Rr]);
                
                this.programCounter++;
                
                clockCount = 2;
                
                break;
            }
            /**
             * ST.b
             * 
             * Writes an 8-bit value contained in a given register to the memory adress pointed
             * to by another given register.
             */
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
            /**
             * MOV.b
             * 
             * Coppies the contents of one register to another.
             */
            case OPCODES.MOV_b:
            {
                char Rr = (char)(instr & 0xFF);
                char Rd = (char)((instr >> 8) & 0xFF);
                
                this.mem.registers[Rd] = (char)(this.mem.registers[Rr] & 0xFF);
                
                this.programCounter++;
                
                clockCount = 1;
                
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
            /**
             * JMP.b
             * 
             * Sets the program counter to a given constant address (16-bit).
             */
            case OPCODES.JMP_b:
            {
                char k = (char)(instr & 0xFFFF);
                
                this.programCounter = k;
                
                clockCount = 3;
                
                break;
            }
            /**
             * RJMP.b
             * 
             * Adds a 12 bit signed number to the program counter.
             */
            case OPCODES.RJMP_b:
            {
                char k = (char)(instr & 0xFFF);
                
                this.programCounter += (k - 2047) + 1;
                
                clockCount = 2;
                
                break;
            }
            /**
             * IJMP.b
             * 
             * Jump to the address in the given register.
             */
            case OPCODES.IJMP_b:
            {
                char Rl = (char)(instr & 0xFF);
                
                this.programCounter = (char)(this.mem.registers[Rl] & (this.mem.registers[Rl + 1] << 8));
                
                clockCount = 2;
                
                break;
            }
            case OPCODES.BRBS_b:
            {
                char k = (char)(instr & 0xFF);
                char b = (char)((instr >> 8) & 0x07);
                
                if ((this.mem.io[IO.SREG] & (1 << b)) != 0) // If b bit in SREG is set
                {
                    this.programCounter += (k - 127) + 1;
                    clockCount = 2;
                }
                else  // If b bit in SREG is clear
                {
                    this.programCounter++;
                    clockCount = 1;
                }
                
                break;
            }
            case OPCODES.BRBC_b:
            {
                char k = (char)(instr & 0xFF);
                char b = (char)((instr >> 8) & 0x07);
                
                if ((this.mem.io[IO.SREG] & (1 << b)) == 0) // If b bit in SREG is clear
                {
                    this.programCounter += (k - 127) + 1;
                    clockCount = 2;
                }
                else  // If b bit in SREG is set
                {
                    this.programCounter++;
                    clockCount = 1;
                }
                
                break;
            }
            /**
             * NOP
             * 
             * Do nothing for 1 clock cycle.
             */
            case OPCODES.NOP:
            {
                clockCount = 1;
                
                this.programCounter += 1;
            }
            /**
             * EOF
             * 
             * Freeze the program. The program counter does not reset or increment.
             */
            case OPCODES.EOF:
            {   
                clockCount = 1;
                
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

        // Give each peripheral a chance to do something.
        for (Peripheral p : this.peripherals)
        {
            p.clock();
        }
        
        // Return the number of clock cycles that the processor took.
        return clockCount;   
    }
}
