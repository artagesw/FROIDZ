import java.lang.NumberFormatException;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Represents a binary number using a String and performs operations on it.
 * 
 * @author Jacob Weiss
 * @version 0.0.6
 */
public class Binary
{   
    private String value = ""; // Holds the value of the binary number.
    
    static HashMap<String, Integer> bases = initBases();
    private static HashMap<String, Integer> initBases()
    {
        HashMap<String, Integer> bases = new HashMap();
        bases.put("0x", 16);
        bases.put("0b", 2);
        return bases;
    }
    
    /**
     * Binary(String input)
     * 
     * Create a binary object from a given input string. For the rest of its life,
     * the new binary object will maintain the same number of bits that it is 
     * given in this constructor. With hex, each character is represented by
     * 4 bits. In binary, every bit is remembered.
     * 
     * @param  value is a String that represents a number
     *         valid formats: hex -> "0x{0-F}"
     *                        bin -> "0b{0-1}"
     */
    public Binary(String input)
    {
        try
        {
            this.value = stringToBinaryString(input.substring(2), bases.get(input.substring(0, 2)));
        }
        catch (Throwable e)
        {
            throw new NumberFormatException();
        }
    } 
    /**
     * Binary(int input, int minNumBits)
     * 
     * The number of bits remembered is at least minNumBits.
     * 
     * @param  value is a base 10 int
     *         valid formats: dec -> {0-9}
     * @param  minNumBits  the number of bits to remember, at least.
     */
    public Binary(Integer input, int minNumBits)
    {
        this("0b" + pad(Integer.toBinaryString(input), minNumBits));
    }
    /**
     * Binary(int input)
     * 
     * The number of bits remembered is the least possible to represent the decimal
     * number in binary.
     * 
     * @param  value is a base 10 int
     *         valid formats: dec -> {0-9}
     */
    public Binary(Integer input)
    {
        this("0b" + Integer.toBinaryString(input));
    }    
    public Binary()
    {
        this.value = "";
    }
    
    // ***********************************Static methods**********************************
     
    /**
     * stringToBinaryString(String in, int base)
     * 
     * Convert a string into the binary representation of it.
     * PRECONDITION: base is an integer power of 2.
     *      
     * @param   String   the string to convert
     * @param   int      the base of the input
     * @return  String   a binary representation of the input
     *      
     */
    public static String stringToBinaryString(String in, int base)
    {
        double baseConvert = Math.log(base)/Math.log(2);
        if (base != (int)base)
        {
            throw new java.lang.NumberFormatException();
        }
        String binary = "";
        for (int i = 0; i < in.length(); i++)
        {
            binary = binary + pad(Integer.toBinaryString(Integer.parseInt(in.substring(i, i + 1), base)), (int)baseConvert);
        }
        return binary;
    }
    
    /**
     * pad(String s, int numChars)
     * 
     * Pad the beginning of s with "0"s until it is numChars length
     * 
     * @param  String    the input string (normally hex or binary)
     * @param  numChars  the desired length of the input string
     * @return String    the new string with preceding "0"s or...
     *                      the input string if numChars <= s.length()
     */
    private static String pad(String s, int numChars)
    {
        while (s.length() < numChars)
        {
            s = "0" + s;
        }
        return s;
    }   
    
    // ***********************************Instance methods**********************************
    
    /**
     * concatBack(Binary... args)
     * 
     * Concatenates the given binary objects to the end of this.
     * ***Modifies this***
     * 
     * Eg. this.toBinaryString() = "1111"
     *     bin2.toBinaryString() = "0000"
     *     this.concatBack(bin2, bin2);
     *     this.toBinaryString() = "111100000000"
     * 
     * @param  Binary  The binary objects to append to this one.
     */
    public void concatBack(Binary... args)
    {
        for (Binary b : args)
        {
            this.value = this.value + b.value;
        }
    }
    
    /**
     * concatFront(Binary... args)
     * 
     * Concatenates the given binary objects to the front of this.
     * ***Modifies this***
     * 
     * Eg. this.toBinaryString() = "1111"
     *     bin2.toBinaryString() = "1100"
     *     this.concatFront(bin2, this);
     *     this.toBinaryString() = "110011111111"
     * 
     * @param  Binary  The binary objects to add in front of this one.
     */
    public void concatFront(Binary... args)
    {
        for (Binary b : args)
        {
            this.value = b.value + this.value;
        }
    }
    
    /**
     * add(Binary... args)
     * 
     * Adds the given Binary objects to this.
     * 
     * @param   Binary(s)  The binary objects to add.
     */
    public void add(Binary... args)
    {        
        for (Binary b : args)
        {
            this.value = add(this.value, b.value, "0");
        }
    }    
    private static String add(String b0, String b1, String c)
    {
        if (b0.length() == 0)
        {
            if (c.equals("0"))
            {
                return b1;
            }
            else if (b1.length() == 0)
            {
                return "1";
            }
            else
            {
                b0 = "1";
                c = "0";
            }
        }
        else if (b1.length() == 0)
        {
            if (c.equals("0"))
            {
                return b0;
            }
            else if (b0.length() == 0)
            {
                return "1";
            }
            else
            {
                b1 = "1";
                c = "0";
            }
        }
        
        String lsb0 = b0.substring(b0.length() - 1);
        String lsb1 = b1.substring(b1.length() - 1);
        
        if (lsb0.equals("1") && lsb1.equals("1"))
        {
            if (c.equals("1"))
            {
                return add(b0.substring(0, b0.length() - 1), b1.substring(0, b1.length() - 1), "1") + "1";
            }
            else
            {
                return add(b0.substring(0, b0.length() - 1), b1.substring(0, b1.length() - 1), "1") + "0";
            }
        }
        else if (lsb0.equals("1") || lsb1.equals("1"))
        {
            if (c.equals("1"))
            {
                return add(b0.substring(0, b0.length() - 1), b1.substring(0, b1.length() - 1), "1") + "0";
            }
            else
            {
                return add(b0.substring(0, b0.length() - 1), b1.substring(0, b1.length() - 1), "0") + "1";
            }
        }
        else
        {
            if (c.equals("1"))
            {
                return add(b0.substring(0, b0.length() - 1), b1.substring(0, b1.length() - 1), "0") + "1";
            }
            else
            {
                return add(b0.substring(0, b0.length() - 1), b1.substring(0, b1.length() - 1), "0") + "0";
            }
        }
    }
    
    /**
     * leastSignificantByte()
     * 
     * Returns the 8 least significant bits of this.
     * 
     * @return  Binary  the least significant 8 bits
     */
    public Binary leastSignificantByte()
    {
        return new Binary("0b" + this.value.substring(this.value.length() - 4));
    }
    
    /**
     * twoComp()
     * 
     * Returns the two's complement of this. 
     * The length of the value returned is equal to the length of this.
     * that this can be represented in. Leading 0s are ignored.
     * 
     * @return  Binary  the two's complement of this
     */
    public Binary twoComp()
    {
        char[] c = this.value.toCharArray();
        int i = c.length - 1;
        while (c[i] == '0')
        {
            i--;
        }
        i--;
        while (i >= 0)
        {
            if (c[i] == '0')
            {
                c[i] = '1';
            }
            else
            {
                c[i] = '0';
            }
            i--;
        }
        return new Binary("0b" + String.valueOf(c));
    }
    
    /**
     * toString()
     * 
     * Returns this as a string formatted in hex.
     * 
     * @return  String  the hex representation of this.
     */
    public String toString()
    {
        String bin = pad(this.value, (int)Math.ceil(this.value.length() / 4.0) * 4);
        String hex = "";
        for (int i = 0; i <= bin.length() - 4; i += 4)
        {
            hex = hex + Integer.toHexString(Integer.parseInt(bin.substring(i, i + 4), 2));
        }
        return hex.toUpperCase();
    }
    public String toBinaryString()
    {
        return this.value; 
    }   
    
    /**
     * split(int start, int end)
     * 
     * Acts like substring. Returns a new binary from [start, end)
     * 
     * @return  Binary  the selected part of this
     */
    public Binary slice(int start, int end)
    {
        return new Binary("0b" + this.value.substring(start, end));
    }
    /**
     * split(int start)
     * 
     * Acts like substring. Returns a new binary from [start, end)
     * 
     * @return  Binary  the selected part of this
     */
    public Binary slice(int start)
    {
        return new Binary("0b" + this.value.substring(start));
    }
    
    /**
     * minNumBits()
     * 
     * Returns the minimum number of bits needed to represent this in base 2.
     * 
     * @return  int   the number of bits
     */
    public int minNumBits()
    {
        return this.value.length() - this.value.indexOf("1");
    }
    public int getNumBits()
    {
        return this.value.length();
    }    
    
    public int getValue()
    {
        return Integer.parseInt(this.value, 2);
    }
}
