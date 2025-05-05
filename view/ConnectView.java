package view;

import controller.ConnectController;
import model.Post;
import model.User;
import java.util.HashSet;
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
            System.out.println("6. Get Following");
            System.out.println("7. Get Followers");
            System.out.println("8. Exit");
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
                    getFollowing();
                    break;
                case 7:
                    getFollowers();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
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
        User user = controller.loggedInUser();
        if (user == null) {
            
            System.out.println("You need to login first.");
            return;
        }
        controller.postMessage(content);
        System.out.println("Message posted!");
    }
    private void getFollowers(){
        User curr = controller.loggedInUser();
        if (curr == null) {
            System.out.println("You need to login first.");
            return;
        }
        HashSet<String> followers = controller.getFollowers(curr.getUsername());
        if (followers != null) {
            System.out.println("Your followers: " + followers);
        } else {
            System.out.println("User not found.");
        }

    }
    private void viewPosts() {
        controller.getPosts().forEach((username, postList) -> {
            System.out.println("Posts by " + username + ":");
            for (Post p : postList) {
                System.out.println("- " + p.getContent());
            }
        });
    }
    
    private void getFollowing() {
        User curr = controller.loggedInUser();
        if (curr == null) {
            System.out.println("You need to login first.");
            return;
        }
        

        HashSet<String> following = controller.getFollowing(curr.getUsername());
        if (following != null) {
            System.out.println("You are following: " + following);
        } else {
            System.out.println("User not found.");
        }
    }

    private void followUser() {
        User curr = controller.loggedInUser();
        if (curr == null) {
            System.out.println("You need to login first.");
            return;
        }
        System.out.print("Enter the username of the user you want to follow: ");    
        String usernameToFollow = scanner.nextLine();
        controller.followUser(curr.getUsername(), usernameToFollow);
        System.out.println("You are now following " + usernameToFollow);
    }
}
