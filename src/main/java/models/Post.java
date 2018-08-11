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

    public static ArrayList<Post> getAll() {
        return instances;
    }

    public static void clearAllPosts(){
        instances.clear();
    }

    public static Post findById(int id) { return instances.get(id - 1); }

    public String getContent() {
        return content;
    }

    public boolean getPublished() { return published; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public int getId() { return id; }
}