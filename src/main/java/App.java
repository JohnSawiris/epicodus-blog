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
        /*****************
         *
         * Static Routes
         *  ******************/
        // Homepage Route
        get("/", (request, response) -> {
           Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Post> posts = Post.getAll();
            // Add Posts To Model
            model.put("posts", posts);
            // Pass POSTS Model To View
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

        // Add New Post Route
        get("/posts/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        // Delete All Post
        get("/posts/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Post.clearAllPosts();
            return new ModelAndView(model, "deleted-successfully.hbs");
        }, new HandlebarsTemplateEngine());

        /*****************
         *
         * Dynamic Routes
         *  ******************/

        // Single Post Route
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

        // Update Form
        get("/posts/:id/update", (req, res) -> {
           Map<String, Object> model = new HashMap<>();
           int idOfPostToEdit = Integer.parseInt(req.params("id"));
           Post editPost = Post.findById(idOfPostToEdit);
           model.put("editPost", editPost);
           return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        // Update Post
        post("/posts/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newContet = req.queryParams("content");
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(newContet);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // Delete Post
        get("/posts/:id/delete", (req, res) -> {
           Map<String, Object> model = new HashMap<>();
           int idOfPostToDelete = Integer.parseInt(req.params("id"));
           Post deletePost = Post.findById(idOfPostToDelete);
           deletePost.deletePost();
           return new ModelAndView(model, "deleted-successfully.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
