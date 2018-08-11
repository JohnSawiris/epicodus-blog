package models;

import java.util.ArrayList;

public class Post {

    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;

    public Post (String content){
        this.content = content;
        this.published = false;
        instances.add(this);
    }

    public static ArrayList<Post> getAll() {
        return instances;
    }

    public static void clearAllPosts(){
        instances.clear();
    }

    public String getContent() {
        return content;
    }

    public boolean getPublished() { return published; }
}