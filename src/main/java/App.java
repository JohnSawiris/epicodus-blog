import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Models
import models.Post;

// Spark
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
            // Add Posts To Model
            model.put("posts", posts);
            // Pass Model To View
           return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Create a new Post
        // Route will receive a request from the form to add post
        post("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            // Extract User Input From Request
            String content = request.queryParams("content");
            Post newPost = new Post(content);
            model.put("post", newPost);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            // Get Post Id from Request
            int idOfPostToFind = Integer.parseInt(req.params(":id"));
            // Find Post in The Instances On The Post Class
            Post foundPost = Post.findById(idOfPostToFind);
            // Pass Post To Model
            model.put("post", foundPost);
            return new ModelAndView(model, "post-detail.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
