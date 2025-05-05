package ui;

import controller.ConnectController;
import model.Post;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Dashboard extends JPanel {
    private ConnectController controller;
    private JTextArea postsArea;
    private JTextArea followingArea;
    private JTextArea followingPostsArea;
    private JTextField postTextField;
    private JTextField followTextField;

    public Dashboard(ConnectController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        User loggedUser = controller.loggedInUser();

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(new JLabel("Logged in as: " + loggedUser.getUsername()), BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Connect Dashboard", SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                ((CardLayout) parent.getLayout()).show(parent, "Register");
            }
        });
        headerPanel.add(backButton, BorderLayout.EAST);
        this.add(headerPanel, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("All Posts", createPostsPanel());
        tabbedPane.addTab("Following Posts", createFollowingPostsPanel());
        tabbedPane.addTab("Following", createFollowingPanel());
        tabbedPane.addTab("Followers", createFollowersPanel());
        this.add(tabbedPane, BorderLayout.CENTER);

        // New Post Panel
        JPanel newPostPanel = new JPanel(new BorderLayout());
        postTextField = new JTextField();

        JButton postButton = new JButton("Post");
        postButton.addActionListener((ActionEvent e) -> {
            String postContent = postTextField.getText().trim();
            if (!postContent.isEmpty()) {
                controller.postMessage(postContent);
                postTextField.setText("");
                JOptionPane.showMessageDialog(this, "Posting successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
                refreshPosts();
                refreshFollowingPosts();
            }
        });

        newPostPanel.add(new JLabel("New Post: "),BorderLayout.WEST);
        newPostPanel.add(postTextField, BorderLayout.CENTER);
        newPostPanel.add(postButton, BorderLayout.EAST);
        this.add(newPostPanel, BorderLayout.SOUTH);

        refreshPosts();
        refreshFollowing();
        refreshFollowingPosts();
    }

    private JPanel createPostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        postsArea = new JTextArea();
        postsArea.setEditable(false);
        panel.add(new JScrollPane(postsArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFollowingPostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        followingPostsArea = new JTextArea();
        followingPostsArea.setEditable(false);
        panel.add(new JScrollPane(followingPostsArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFollowingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        followingArea = new JTextArea();
        followingArea.setEditable(false);
        panel.add(new JScrollPane(followingArea), BorderLayout.CENTER);

        JPanel followPanel = new JPanel(new BorderLayout());
        followTextField = new JTextField();
        JButton followButton = new JButton("Follow");

        followButton.addActionListener((ActionEvent e) -> {
            String usernameToFollow = followTextField.getText().trim();
            if (!usernameToFollow.isEmpty()) {
                User loggedUser = controller.loggedInUser();
                controller.followUser(loggedUser.getUsername(), usernameToFollow);
                followTextField.setText("");
                refreshFollowing();
                refreshFollowingPosts();
            }
        });

        followPanel.add(new JLabel("Follow User: "), BorderLayout.WEST);
        followPanel.add(followTextField, BorderLayout.CENTER);
        followPanel.add(followButton, BorderLayout.EAST);
        panel.add(followPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshPosts() {
        postsArea.setText("");
        Map<String, ArrayList<Post>> allPosts = controller.getPosts();

        if (allPosts.isEmpty()) {
            postsArea.append("No posts available\n");
        } else {
            allPosts.forEach((username, userPosts) -> {
                for (Post post : userPosts) {
                    postsArea.append(username + ": " + post.getContent() + "\n\n");
                }
            });
        }
    }

    private void refreshFollowingPosts() {
        followingPostsArea.setText("");
        Map<String, ArrayList<Post>> allPosts = controller.getPosts();
        User loggedUser = controller.loggedInUser();
        HashSet<String> following = controller.getFollowing(loggedUser.getUsername());

        if (following == null || following.isEmpty()) {
            followingPostsArea.append("You are not following anyone.\n");
            return;
        }

        boolean hasPosts = false;
        for (String username : following) {
            ArrayList<Post> posts = allPosts.get(username);
            if (posts != null) {
                for (Post post : posts) {
                    followingPostsArea.append(username + ": " + post.getContent() + "\n\n");
                    hasPosts = true;
                }
            }
        }

        if (!hasPosts) {
            followingPostsArea.append("No posts from people you follow.\n");
        }
    }
    private JPanel createFollowersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea followersArea = new JTextArea();
        followersArea.setEditable(false);
        panel.add(new JScrollPane(followersArea));
    
        User loggedUser = controller.loggedInUser();
        HashSet<String> followers = controller.getFollowers(loggedUser.getUsername());
    
        if (followers == null || followers.isEmpty()) {
            followersArea.setText("You have no followers\n");
        } else {
            followersArea.setText("Followers:\n");
            for (String username : followers) {
                followersArea.append("- " + username + "\n");
            }
        }
    
        return panel;
    }
    

    private void refreshFollowing() {
        followingArea.setText("");
        User loggedUser = controller.loggedInUser();
        HashSet<String> following = controller.getFollowing(loggedUser.getUsername());

        if (following == null || following.isEmpty()) {
            followingArea.append("You are not following anyone\n");
        } else {
            followingArea.append("Following:\n");
            for (String username : following) {
                followingArea.append("- " + username + "\n");
            }
        }
    }
}
