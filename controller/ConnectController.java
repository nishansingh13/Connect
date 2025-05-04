package controller;
import model.User;
import model.Post;
import java.util.HashMap;
public class ConnectController {
    private  HashMap<String, User> users;
    private HashMap<String, Post> posts;
    public ConnectController() {
        users = new HashMap<>();
        posts = new HashMap<>();
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
                return true; 
            }
        }
        return false; 
    }
    public void postMessage(String username, String content) {
        if (users.containsKey(username)) {
            Post post = new Post(content, username);
            posts.put(username, post);
        }
    }
    public HashMap <String, Post> getPosts() {
        return posts;
    }
    public void followUser(String username, String user_to_follow) {
        User curr = users.get(username);
        if (curr != null) {
            curr.follow(user_to_follow);
        }
    }

    public void unfollowUser(String currentUsername, String usernameToUnfollow) {
        User currentUser = users.get(currentUsername);
        if (currentUser != null) {
            currentUser.unfollow(usernameToUnfollow);
        }
    }
}
