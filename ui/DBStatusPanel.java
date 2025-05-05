package ui;

import java.awt.*;
import javax.swing.*;

import db.ConnectionManager;

public class DBStatusPanel extends JPanel {
    private JLabel statusLabel;
    
    public DBStatusPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        updateStatus();
        
        add(statusLabel);
        
        // Add a refresh button
        JButton refreshButton = new JButton("⟳");
        refreshButton.setToolTipText("Refresh DB connection status");
        refreshButton.addActionListener(e -> updateStatus());
        add(refreshButton);
    }
    
    public void updateStatus() {
        try {
            boolean isConnected = ConnectionManager.getInstance().getConnection() != null && 
                                !ConnectionManager.getInstance().getConnection().isClosed();
            
            if (isConnected) {
                statusLabel.setText("DB Status: Connected");
                statusLabel.setForeground(new Color(0, 128, 0)); // Green
            } else {
                statusLabel.setText("DB Status: Disconnected");
                statusLabel.setForeground(Color.RED);
            }
        } catch (Exception e) {
            statusLabel.setText("DB Status: Error");
            statusLabel.setForeground(Color.RED);
            e.printStackTrace();
        }
    }
}
