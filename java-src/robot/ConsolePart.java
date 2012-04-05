package robot;

import emulator.cpu.IAsynchronousUSART;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Write a description of class Printer here.
 * 
 * @author Jacob Weiss
 * @version 0.1.0
 */
public class ConsolePart extends Part implements IAsynchronousUSART, ActionListener
{   
    JTextField input;
    ButtonGroup type;
    JFrame frame;
    public ConsolePart()
    {
        String title = "Input";
        if (this.robot != null)
        {
            title = this.robot.name();
        }
        this.frame = new JFrame(title);
        this.frame.setSize(300, 60);        
        this.frame.setLayout(new GridBagLayout());
        
        GridBagConstraints c;       
                
        this.input = new JTextField();
        frame.add(this.input);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weighty = 1;
        c.weightx = 1;
        this.frame.add(this.input, c);
        
        JButton sendButton = new JButton("send");
        sendButton.addActionListener(this);
        sendButton.setActionCommand("send");
        sendButton.setDefaultCapable(true);
        this.frame.getRootPane().setDefaultButton(sendButton);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weighty = 1;
        c.weightx = 0;
        this.frame.add(sendButton, c);
        
        this.type = new ButtonGroup();
        JRadioButton ascii = new JRadioButton("ASCII");
        ascii.setActionCommand("ascii");
        JRadioButton num = new JRadioButton("Num");
        num.setActionCommand("number");
        type.add(ascii);
        type.add(num);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weighty = 1;
        c.weightx = 0;
        frame.add(ascii, c);
        c.gridy = 1;
        frame.add(num, c);
        ascii.setSelected(true);
        
        frame.setVisible(true);
    }
    
    public Part setName(String name)
    {
        this.frame.setTitle(name);
        return super.setName(name);
    }        
    
    public void actionPerformed(ActionEvent e)
    {
        String format = this.type.getSelection().getActionCommand();
        if (format.equals("ascii"))
        {
            for (char c : input.getText().toCharArray())
            {
                this.device.Rx((byte)c);
            }
        }
        else if (format.equals("number"))
        {
            try
            {
                this.device.Rx((byte)Integer.parseInt(input.getText()));
            }
            catch (java.lang.NumberFormatException exception)
            {
                System.out.println("Invalid Number Formatting");
            }
        }
        input.setText("");
    }
    
    public void Rx(byte data)
    {
        System.out.print((char)data);
    }
}
