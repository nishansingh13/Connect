package ui;

import controller.ConnectController;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DBStatusPanel dbStatusPanel;

    public MyFrame(ConnectController controller) {
        this.setTitle("Connect");
        this.setSize(600, 500); // Made a bit wider to accommodate DB status
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Create main container with a border layout
        JPanel containerPanel = new JPanel(new BorderLayout());
        
        // Add DB status panel at the bottom
        dbStatusPanel = new DBStatusPanel();
        containerPanel.add(dbStatusPanel, BorderLayout.SOUTH);
        
        // Main content area with card layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        containerPanel.add(mainPanel, BorderLayout.CENTER);

        // Welcome Screen
        JPanel welcomeScreen = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Connect!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton goToRegisterButton = new JButton("Register/Login");
        goToRegisterButton.setFont(new Font("Arial", Font.PLAIN, 16));
        goToRegisterButton.setBackground(new Color(0, 123, 255));
        goToRegisterButton.setForeground(Color.WHITE);
        goToRegisterButton.setFocusPainted(false);

        goToRegisterButton.addActionListener(e -> cardLayout.show(mainPanel, "Register"));

        welcomeScreen.add(welcomeLabel, BorderLayout.CENTER);
        welcomeScreen.add(goToRegisterButton, BorderLayout.SOUTH);

        Register registerScreen = new Register(controller, mainPanel, cardLayout);

        mainPanel.add(welcomeScreen, "WelcomeScreen");
        mainPanel.add(registerScreen, "Register");
       
        this.add(containerPanel);
        this.setVisible(true);

 
        cardLayout.show(mainPanel, "WelcomeScreen");
    }
}
