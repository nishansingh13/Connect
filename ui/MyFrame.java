package ui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        this.setTitle("Connect");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("images/logo.jpg");
        this.setIconImage(icon.getImage());

        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel titleLabel = new JLabel("Welcome to Connect!");
        // titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));


        this.add(logoLabel, BorderLayout.NORTH);
        // this.add(centerPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
