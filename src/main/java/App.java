import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String args[]) {
        staticFileLocation("/public");
        // Homepage route
        get("/", (request, response) -> {
           Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Post> posts = Post.getAll();
            model.put("posts", posts);
           return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Create a new Post
        post("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            Post newPost = new Post(content);
            model.put("post", newPost);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}