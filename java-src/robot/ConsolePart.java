package robot;

import emulator.cpu.IAsynchronousUSART;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.GridLayout;
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
    public ConsolePart()
    {
        String title = "Input";
        if (this.robot != null)
        {
            title = this.robot.name();
        }
        JFrame frame = new JFrame(title);
        frame.setSize(300, 50);
        frame.setLayout(new GridLayout(1, 2)); // 4 rows, 1 collumn
        
        input = new JTextField();
        frame.add(input);
        
        JButton sendButton = new JButton("send");
        sendButton.addActionListener(this);
        sendButton.setActionCommand("send");
        frame.add(sendButton);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        for (char c : input.getText().toCharArray())
        {
            this.device.Rx((byte)c);
        }
        input.setText("");
    }
    
    public void Rx(byte data)
    {
        System.out.print((char)data);
    }
}
