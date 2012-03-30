package emulator.wp;


/**
 * Write a description of class ToastyIO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ToastyIO extends IO
{
    public static final int UDR2 = 158;
    public static final int UCSR2A = 159;
    public static final int UDR3 = 160;
    public static final int UCSR3A = 161;
    public static final int UDR4 = 162;
    public static final int UCSR4A = 163;
    public static final int UDR5 = 164;
    public static final int UCSR5A = 165;
    public static final int UDR6 = 166;
    public static final int UCSR6A = 167;
    public static final int UDR7 = 168;
    public static final int UCSR7A = 169;
    
    public static final int PWM0 = IO.OCR3AH;
    public static final int PWM1 = IO.OCR3AL;
    public static final int PWM2 = IO.OCR3BH;
    public static final int PWM3 = IO.OCR3BL;
    public static final int PWM4 = IO.OCR3CH;
    public static final int PWM5 = IO.OCR3CL;
}
