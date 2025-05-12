package ui;

import javax.swing.*;
import java.awt.*;
import controller.ConnectController;

public class Register extends JPanel {
    public Register(ConnectController controller, JPanel mainPanel, CardLayout cardLayout) {
   
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
       

     
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
       
            try {
                if (controller.registerUser(username, email, password)) {
                    JOptionPane.showMessageDialog(this, "Registration successful!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                 
                    usernameField.setText("");
                    emailField.setText("");
                    passwordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "User already exists or database error.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Database error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            
            }
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Username and password are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                if (controller.loginUser(username, password)) {
                    JOptionPane.showMessageDialog(this, "Login successful!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    Dashboard dashboardPanel = new Dashboard(controller);
                    mainPanel.add(dashboardPanel, "WelcomePanel");
                    cardLayout.show(mainPanel, "WelcomePanel");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Database error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
             
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
