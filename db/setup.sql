-- Database schema for Connect application

-- Users table
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

-- Posts table
CREATE TABLE IF NOT EXISTS posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    author VARCHAR(255) NOT NULL,
    FOREIGN KEY (author) REFERENCES users(username)
);

-- Following relationships table
CREATE TABLE IF NOT EXISTS follows (
    follower VARCHAR(255),
    followee VARCHAR(255),
    PRIMARY KEY (follower, followee),
    FOREIGN KEY (follower) REFERENCES users(username),
    FOREIGN KEY (followee) REFERENCES users(username)
);
