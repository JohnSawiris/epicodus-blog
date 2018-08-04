package models;

import java.util.ArrayList;

public class Post {

    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();

    public Post (String content){
        this.content = content;
        instances.add(this);
    }

    public static ArrayList<Post> getAll() {
        return instances;
    }

    public static void clearAllPosts(){
        instances.clear();
    }

    public String getContent() {
        return this.content;
    }
}