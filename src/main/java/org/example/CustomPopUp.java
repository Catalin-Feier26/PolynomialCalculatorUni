package org.example;
import javax.swing.*;
import java.awt.*;

public class CustomPopUp extends JDialog {
    public CustomPopUp(JFrame parent, String message, Color color)
    {
        super(parent,message,true);

        JLabel label = new JLabel("<html><div style='text-align: center; width: 200px;'>" + message + "</div></html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);

        JPanel panel=new JPanel(new BorderLayout());
        panel.add(label,BorderLayout.CENTER);
        panel.setBackground(color);

        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,150);
        setLocationRelativeTo(parent);
        setVisible(true);

    }
}
