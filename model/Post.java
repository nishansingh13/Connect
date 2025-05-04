package model;
import java.util.ArrayList;
public class Post {
    private String content;
    private String author;
    private ArrayList<String> comments;
    public Post(String content, String author) {
        this.content = content;
        this.author = author;
        this.comments = new ArrayList<>();
    }
    public String getContent(){
        return content;
    }
    public String getAuthor(){
        return author;
    }
    
    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }
}
