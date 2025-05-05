package ui;

import javax.swing.*;
import java.awt.*;
import controller.ConnectController;

public class Register extends JPanel {
    public Register(ConnectController controller, JPanel mainPanel, CardLayout cardLayout) {
        // Set the layout for the Register panel
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  
        gbc.anchor = GridBagConstraints.WEST;  
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 123, 255));
        registerButton.setForeground(Color.WHITE);
        

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
       

        // Action for the Register button
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (controller.registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (controller.loginUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Dashboard welcomePanel = new Dashboard(controller);
                mainPanel.add(welcomePanel, "WelcomePanel");
                cardLayout.show(mainPanel, "WelcomePanel");
            } else {
                JOptionPane.showMessageDialog(this, "Login failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0; gbc.gridy = 0;
        this.add(usernameLabel, gbc);
        gbc.gridx = 1;
        this.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        this.add(emailLabel, gbc);
        gbc.gridx = 1;
        this.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        this.add(passwordLabel, gbc);
        gbc.gridx = 1;
        this.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        this.add(registerButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        this.add(loginButton, gbc); 
    }
}
