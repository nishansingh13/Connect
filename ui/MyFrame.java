package ui;

import controller.ConnectController;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MyFrame(ConnectController controller) {
        this.setTitle("Connect");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Welcome Screen
        JPanel welcomeScreen = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Connect!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton goToRegisterButton = new JButton("Register");
        goToRegisterButton.setFont(new Font("Arial", Font.PLAIN, 16));
        goToRegisterButton.setBackground(Color.pink);
        goToRegisterButton.setForeground(Color.WHITE);
        goToRegisterButton.setFocusPainted(false);

        goToRegisterButton.addActionListener(e -> cardLayout.show(mainPanel, "Register"));

        welcomeScreen.add(welcomeLabel, BorderLayout.CENTER);
        welcomeScreen.add(goToRegisterButton, BorderLayout.SOUTH);

      
        Register registerScreen = new Register(controller,mainPanel,cardLayout);

        mainPanel.add(welcomeScreen, "WelcomeScreen");
        mainPanel.add(registerScreen, "Register");
       

        this.add(mainPanel);
        this.setVisible(true);

        // Show the WelcomeScreen by default
        cardLayout.show(mainPanel, "WelcomeScreen");
    }
}
