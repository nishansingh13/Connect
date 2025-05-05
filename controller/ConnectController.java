package controller;
import model.User;
import model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
public class ConnectController {
    private  HashMap<String, User> users;
    private HashMap<String, ArrayList<Post>> posts;
    private User loggedUser;
    public ConnectController() {
        users = new HashMap<>();
        posts = new HashMap<>();
        loggedUser = null;
    }
    public boolean registerUser(String username , String password){
        if(users.containsKey(username)){
            return false; 
        }
        users.put(username, new User(username, password));
        return true; 
    }
    public boolean loginUser(String username , String password){
        if(users.containsKey(username)){
            User user = users.get(username);
            if(user.getPassword().equals(password)){
                loggedUser = user;
                return true; 
            }
        }
        return false; 
    }
    public void postMessage(String content) {
        if (loggedUser != null) {
            Post post = new Post(content, loggedUser.getUsername());
            posts.putIfAbsent(loggedUser.getUsername(), new ArrayList<>());
            posts.get(loggedUser.getUsername()).add(post);
        }
    }
    
    public HashMap <String, ArrayList<Post>> getPosts() {
        return posts;
    }
    public void followUser(String username, String userToFollow) {
        User follower = users.get(username);        
        User followee = users.get(userToFollow);    
    
        if (follower != null && followee != null && !username.equals(userToFollow)) {
            if (!follower.getFollowing().contains(userToFollow)) {
                follower.follow(userToFollow);
                followee.getFollowers().add(username);  
            }
        }
    }
    

    public void unfollowUser(String currentUsername, String usernameToUnfollow) {
        User currentUser = users.get(currentUsername);
        if (currentUser != null) {
            currentUser.unfollow(usernameToUnfollow);
          
        }
    }
    public HashSet<String> getFollowing(String username) {
        User user = users.get(username);
        if (user != null) {
            return user.getFollowing();
        }
        return null;
    }
    public HashSet<String>getFollowers(String username){
        User user = users.get(username);
        if(user!=null) return user.getFollowers();
        return null;
    }

    public User loggedInUser() {
        return loggedUser;
    }
}
