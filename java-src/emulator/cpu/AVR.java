package emulator.cpu;

import emulator.wp.IO;
import emulator.wp.ToastyIO;
/**
 * Write a description of class AVR here.
 * 
 * @author Jacob Weiss 
 * @version 0.0.1
 */
public class AVR extends FROIDZCPU
{    
    private Peripheral[] peripheralMap;
    private static final int[] usartLookup = {
                                       ToastyIO.UDR0, 
                                       ToastyIO.UDR1, 
                                       ToastyIO.UDR2,
                                       ToastyIO.UDR3,
                                       ToastyIO.UDR4,
                                       ToastyIO.UDR5,
                                       ToastyIO.UDR6,
                                       ToastyIO.UDR7,
                                      };
    
    public AVR()
    {
        Memory mem = new AVRMemory();
        this.proc = new ToastyProcessor(mem, 1);
        this.peripheralMap = new Peripheral[mem.io.length];
        this.initializePeripherals();
    }
    public AVR(String path)
    {
        this();
        this.proc.mem.loadBin(path);        
    }
    
    public void initializePeripherals()
    {
        USART u0 = new USART(this.proc.mem, IO.UDR0, IO.UCSR0A);
        USART u1 = new USART(this.proc.mem, IO.UDR1, IO.UCSR1A);
        USART u2 = new USART(this.proc.mem, ToastyIO.UDR2, ToastyIO.UCSR2A);
        USART u3 = new USART(this.proc.mem, ToastyIO.UDR3, ToastyIO.UCSR3A);
        USART u4 = new USART(this.proc.mem, ToastyIO.UDR4, ToastyIO.UCSR4A);
        USART u5 = new USART(this.proc.mem, ToastyIO.UDR5, ToastyIO.UCSR5A);
        USART u6 = new USART(this.proc.mem, ToastyIO.UDR6, ToastyIO.UCSR6A);
        USART u7 = new USART(this.proc.mem, ToastyIO.UDR7, ToastyIO.UCSR7A);
        
        PWM pwm0 = new PWM(this.proc.mem, IO.OCR3AH);
        PWM pwm1 = new PWM(this.proc.mem, IO.OCR3AL);
        PWM pwm2 = new PWM(this.proc.mem, IO.OCR3BH);
        PWM pwm3 = new PWM(this.proc.mem, IO.OCR3BL);
        PWM pwm4 = new PWM(this.proc.mem, IO.OCR3CH);
        PWM pwm5 = new PWM(this.proc.mem, IO.OCR3CL);
        
        Port porta = new Port(this.proc.mem, 8, IO.PORTA, IO.PINA, IO.DDRA);
        Port portb = new Port(this.proc.mem, 8, IO.PORTB, IO.PINB, IO.DDRB);
        Port portc = new Port(this.proc.mem, 8, IO.PORTC, IO.PINC, IO.DDRC);
        Port portd = new Port(this.proc.mem, 8, IO.PORTD, IO.PIND, IO.DDRD);
        
        this.peripheralMap[IO.UDR1] = u1;
        this.peripheralMap[IO.UDR0] = u0;
        this.peripheralMap[ToastyIO.UDR2] = u2;
        this.peripheralMap[ToastyIO.UDR3] = u3;
        this.peripheralMap[ToastyIO.UDR4] = u4;
        this.peripheralMap[ToastyIO.UDR5] = u5;
        this.peripheralMap[ToastyIO.UDR6] = u6;
        this.peripheralMap[ToastyIO.UDR7] = u7;
        this.peripheralMap[IO.OCR3AH] = pwm0;
        this.peripheralMap[IO.OCR3AL] = pwm1;
        this.peripheralMap[IO.OCR3BH] = pwm2;
        this.peripheralMap[IO.OCR3BL] = pwm3;
        this.peripheralMap[IO.OCR3CH] = pwm4;
        this.peripheralMap[IO.OCR3CL] = pwm5;
        this.peripheralMap[IO.PORTA] = porta;
        this.peripheralMap[IO.PORTB] = portb;
        this.peripheralMap[IO.PORTC] = portc;
        this.peripheralMap[IO.PORTD] = portd;
        
        this.proc.peripherals.add(u1);
        this.proc.peripherals.add(u0);
        this.proc.peripherals.add(u2);
        this.proc.peripherals.add(u3);
        this.proc.peripherals.add(u4);
        this.proc.peripherals.add(u5);
        this.proc.peripherals.add(u6);
        this.proc.peripherals.add(u7);
        this.proc.peripherals.add(pwm0);
        this.proc.peripherals.add(pwm1);
        this.proc.peripherals.add(pwm2);
        this.proc.peripherals.add(pwm3);
        this.proc.peripherals.add(pwm4);
        this.proc.peripherals.add(pwm5);
        this.proc.peripherals.add(porta);
        this.proc.peripherals.add(portb);
        this.proc.peripherals.add(portc);
        this.proc.peripherals.add(portd);
    }

    public void connectToSerial(IUSART usart, int udr)
    {        
        ((USART)(this.peripheralMap[this.usartLookup[udr]])).connect(usart);
    }
    public void connectToPWM(PinConnector p, int address)
    {
        ((PWM)(this.peripheralMap[address])).connect(p);
    }
    public void connectToPin(Connector<Boolean> c, int address, int bit)
    {
        ((Port)(this.peripheralMap[address])).connect(c, bit);
    }
}
