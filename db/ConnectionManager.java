package db;

import java.sql.*;
import java.util.Scanner;

public class ConnectionManager {
    private static DBController db;

    public static void main(String[] args) {
        // Define connection parameters
        String url = "jdbc:mysql://localhost:3306/connectApp?serverTimezone=UTC";
        String username = "nishan";
        String password = "Nishan@22"; 
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
          
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Initialize DBController with the connection
            db = new DBController(connection);
            System.out.println("Connection successful!");
            System.out.println("Database: " + connection.getCatalog());
          
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to ConnectApp!");
            System.out.println("1. Register");
            System.out.println("2. Login");

       
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                // Register a new user
                System.out.println("Enter username:");
                String usernameInput = scanner.nextLine();
                System.out.println("Enter email:");
                String email = scanner.nextLine();
                System.out.println("Enter password:");
                String passwordInput = scanner.nextLine();

                if (db.addUser(usernameInput, email, passwordInput)) {
                    System.out.println("User registered successfully!");
                } else {
                    System.out.println("Registration failed. Try again.");
                }
            } else if (choice == 2) {
                // Login existing user
                System.out.println("Enter email:");
                String email = scanner.nextLine();
                System.out.println("Enter password:");
                String passwordInput = scanner.nextLine();

                if (db.verifyLogin(email, passwordInput)) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid email or password.");
                }
            } else {
                System.out.println("Invalid choice. Please choose 1 for Register or 2 for Login.");
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        }
    }
}
