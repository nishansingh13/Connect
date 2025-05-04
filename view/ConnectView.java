package view;

import controller.ConnectController;
import java.util.Scanner;

public class ConnectView {
    private Scanner scanner;
    private ConnectController controller;

    public ConnectView(ConnectController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    public void showMenu() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Post a Message");
            System.out.println("4. View Posts");
            System.out.println("5. Follow a User");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    postMessage();
                    break;
                case 4:
                    viewPosts();
                    break;
                case 5:
                    followUser();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = controller.registerUser(username, password);
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("User already exists.");
        }
    }

    private void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = controller.loginUser(username, password);
        if (success) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void postMessage() {
        System.out.print("Enter your message: ");
        String content = scanner.nextLine();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        controller.postMessage(username, content);
        System.out.println("Message posted!");
    }

    private void viewPosts() {
        controller.getPosts().forEach((key, post) -> {
            System.out.println("Post by " + key + ": " + post.getContent());
        });
    }

    private void followUser() {
        System.out.print("Enter your username: ");
        String currentUsername = scanner.nextLine();
        System.out.print("Enter the username you want to follow: ");
        String usernameToFollow = scanner.nextLine();

        controller.followUser(currentUsername, usernameToFollow);
        System.out.println("You are now following " + usernameToFollow);
    }
}
