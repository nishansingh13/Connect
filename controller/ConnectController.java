package controller;

import model.User;
import model.Post;
import db.DBController;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ConnectController {
    private DBController db;
    private User loggedUser;

    public ConnectController() {
    
        this.db = new DBController();
    }

    public ConnectController(Connection connection) {
       
        this.db = new DBController();
        loggedUser = null;
    }
    
    public boolean registerUser(String username, String email, String password) {
        try {
       
            return db.registerUser(username, email, password);
        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }
  
    public boolean registerUser(String username, String password) {
      
        String email = username + "@connect.com";
        return registerUser(username, email, password);
    }
    
    public boolean loginUser(String username, String password) {
        try {
       
            if (db.validateLogin(username, password)) {
                User user = db.getUser(username);
                if (user != null) {
                    loggedUser = user;
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
           
        }
        return false;
    }
    
    public void postMessage(String content) {
        if (loggedUser != null) {
            Post post = new Post(content, loggedUser.getUsername());
            db.savePost(post);
        }
    }
    
    public HashMap<String, ArrayList<Post>> getPosts() {
        try {
           
            HashMap<String, List<Post>> originalPosts = db.getAllPostsGroupedByUser();
            
          
            HashMap<String, ArrayList<Post>> convertedPosts = new HashMap<>();
            
            for (Map.Entry<String, List<Post>> entry : originalPosts.entrySet()) {
                ArrayList<Post> postsList = new ArrayList<>(entry.getValue());
                convertedPosts.put(entry.getKey(), postsList);
            }
            
            return convertedPosts;
        } catch (Exception e) {
            System.out.println("Error fetching posts: " + e.getMessage());
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    
    public void followUser(String username, String userToFollow) {
        db.followUser(username, userToFollow);
    }
    
    public HashSet<String> getFollowing(String username) {
       
        return db.getFollowing(username);
    }
    
    public HashSet<String> getFollowers(String username) {
    
        return db.getFollowers(username);
    }

    public User loggedInUser() {
        return loggedUser;
    }
}
