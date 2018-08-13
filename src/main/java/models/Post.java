package models;

import java.time.LocalDateTime;

import java.util.ArrayList;

public class Post {

    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;
    private int id;

    public Post (String content){
        this.content = content;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = this.instances.size();
    }
    // Get all the Posts instances
    public static ArrayList<Post> getAll() {
        return instances;
    }
    // Clear Posts instances
    public static void clearAllPosts(){
        instances.clear();
    }
    // Find a Post by ID
    // * (id - 1) to get the correct index
    public static Post findById(int id) { return instances.get(id - 1); }
    // Get Post Content
    public String getContent() {
        return content;
    }
    // Published status
    public boolean getPublished() { return published; }
    // Created on Date
    public LocalDateTime getCreatedAt() { return createdAt; }
    // Post ID
    public int getId() { return id; }
    // Update Post
    public String update(String content) { return this.content = content; }
    // Delete Post
    public void deletePost() { instances.remove(id - 1); }
}