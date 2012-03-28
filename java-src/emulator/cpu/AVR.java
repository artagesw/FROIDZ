package emulator.cpu;

import emulator.wp.IO;
/**
 * Write a description of class AVR here.
 * 
 * @author Jacob Weiss 
 * @version 0.0.1
 */
public class AVR extends FROIDZCPU
{    
    public Peripheral[] peripheralMap;
    
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
        USART u = new USART(this.proc.mem, IO.UDR1, IO.UCSR1A);
        
        PWM pwm0 = new PWM(this.proc.mem, IO.OCR3AH);
        PWM pwm1 = new PWM(this.proc.mem, IO.OCR3AL);
        PWM pwm2 = new PWM(this.proc.mem, IO.OCR3BH);
        PWM pwm3 = new PWM(this.proc.mem, IO.OCR3BL);
        PWM pwm4 = new PWM(this.proc.mem, IO.OCR3CH);
        PWM pwm5 = new PWM(this.proc.mem, IO.OCR3CL);
        
        Port porta = new Port(this.proc.mem, 8, IO.PORTA, IO.PINA, IO.DDRA);
        Port portb = new Port(this.proc.mem, 8, IO.PORTB, IO.PINB, IO.PINB);
        Port portc = new Port(this.proc.mem, 8, IO.PORTC, IO.PINC, IO.DDRC);
        Port portd = new Port(this.proc.mem, 8, IO.PORTD, IO.PIND, IO.DDRD);
        
        this.peripheralMap[IO.UDR1] = u;
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
        
        this.proc.peripherals.add(u);
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
        ((USART)(this.peripheralMap[udr])).connect(usart);
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
