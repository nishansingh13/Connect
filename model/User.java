package model;

import java.util.HashSet;

public class User {
    private String username;
    private String password;
    private HashSet<String> following;
    private HashSet<String> followers;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashSet<String> getFollowing() {
        return following;
    }

    public void follow(String username) {
        following.add(username);
    }

    public void unfollow(String username) {
        following.remove(username);
    }
    

    public HashSet<String> getFollowers() {
        return followers;
    }

}
