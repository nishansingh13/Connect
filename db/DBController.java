package db;

import java.sql.*;

public class DBController {
    private Connection connection;

    public DBController(Connection connection) {
        this.connection = connection;
    }

    // Method to register a new user
    public boolean addUser(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password); // Store password as is (not secure for production)
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get user by email (used for login verification)
    public ResultSet getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to verify login credentials
    public boolean verifyLogin(String email, String password) {
        ResultSet rs = getUserByEmail(email);
        try {
            if (rs != null && rs.next()) {
                String storedPassword = rs.getString("password");
                return password.equals(storedPassword); // Simple comparison (no hashing)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
