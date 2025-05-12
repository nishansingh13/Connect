package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import model.Post;
import model.User;

public class DBController {
    private Connection conn;

    public DBController() {
        try {
            conn = ConnectionManager.getInstance().getConnection();
            createUserTable();
            createPostTable();
            createFollowTable();
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
    }

    private void createUserTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                username VARCHAR(255) PRIMARY KEY,
                password VARCHAR(255) NOT NULL
            );
        """;
        conn.createStatement().execute(sql);
    }

    private void createPostTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS posts (
                id INT AUTO_INCREMENT PRIMARY KEY,
                content TEXT NOT NULL,
                author VARCHAR(255) NOT NULL,
                FOREIGN KEY (author) REFERENCES users(username)
            );
        """;
        conn.createStatement().execute(sql);
    }

    private void createFollowTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS follows (
                follower VARCHAR(255),
                followee VARCHAR(255),
                PRIMARY KEY (follower, followee),
                FOREIGN KEY (follower) REFERENCES users(username),
                FOREIGN KEY (followee) REFERENCES users(username)
            );
        """;
        conn.createStatement().execute(sql);
    }

    // ---------------- USER ----------------

    public boolean registerUser(String username, String password) {
        if (getUser(username) != null) return false;

        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
            return false;
        }
    }

  
    public boolean registerUser(String username, String email, String password) {
        if (getUser(username) != null) return false;

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
            return false;
        }
    }

    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                return new User(username, password);
            }
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
        return null;
    }

    public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
            return false;
        }
    }

    // ---------------- POST ----------------

    public void savePost(Post post) {
        String sql = "INSERT INTO posts (content, author) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, post.getContent());
            stmt.setString(2, post.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
    }

    public HashMap<String, List<Post>> getAllPostsGroupedByUser() {
        HashMap<String, List<Post>> postsByUser = new HashMap<>();
        String sql = "SELECT * FROM posts";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String author = rs.getString("author");
                String content = rs.getString("content");
                Post post = new Post(content, author);

                postsByUser.computeIfAbsent(author, k -> new ArrayList<>()).add(post);
            }
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
        return postsByUser;
    }

    // ---------------- FOLLOW ----------------

    public void followUser(String follower, String followee) {
        String sql = "INSERT IGNORE INTO follows (follower, followee) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, follower);
            stmt.setString(2, followee);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
    }

    public HashSet<String> getFollowers(String username) {
        String sql = "SELECT follower FROM follows WHERE followee = ?";
        HashSet<String> followers = new HashSet<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                followers.add(rs.getString("follower"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching followers "+e.getMessage());
        }
        return followers;
    }

    public HashSet<String> getFollowing(String username) {
        String sql = "SELECT followee FROM follows WHERE follower = ?";
        HashSet<String> following = new HashSet<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                following.add(rs.getString("followee"));
            }
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
        return following;
    }
}
