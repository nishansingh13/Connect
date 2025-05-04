package ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
// import java.awt.Color;

public class MyFrame extends JFrame {
    public MyFrame() {
        this.setTitle("Connect");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

        ImageIcon icon = new ImageIcon("logo.jpg"); 
        this.setIconImage(icon.getImage());
        ImageIcon image = new ImageIcon("logo.jpg");
        // this.getContentPane().setBackground(new Color(123, 50, 250));

        JLabel label = new JLabel();
        label.setIcon(image);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        System.out.println(getClass().getResource("/images/logo.jpg"));

        this.add(label);

        this.setVisible(true); 
    }
}
