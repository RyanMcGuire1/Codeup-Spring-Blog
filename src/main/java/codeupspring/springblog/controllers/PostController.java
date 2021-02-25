package codeupspring.springblog.controllers;

import codeupspring.springblog.Models.Post;
import codeupspring.springblog.Models.User;
import codeupspring.springblog.repositories.PostRepository;
import codeupspring.springblog.repositories.UserRepository;
import codeupspring.springblog.services.EmailService;
import codeupspring.springblog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import codeupspring.springblog.repositories.PostRepository;

import java.util.List;

@Controller
public class PostController {
// Interface injections
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;
    private final UserService userService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService, UserService userService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
        this.userService = userService;
    }
    @GetMapping("/posts")
    public String postsIndex(Model model){

        List<Post> postList = postDao.findAll();

        model.addAttribute("title", "All Posts");
        model.addAttribute("posts", postList);

        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String postView(Model model, @PathVariable long id){
//    get single post by id later
        Post post = postDao.getOne(id);
        model.addAttribute("post",post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model){
        model.addAttribute("post",postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post){
        User user = userService.loggedInUser();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost( Model model,@PathVariable long id){
          postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        // Will throw if no users in the db!
        // In the future, we will get the logged in user!
        User user = userService.loggedInUser();
        post.setUser(user);

        Post savedPost = postDao.save(post);

        String subject = "New post created";
        String body = "Dear " + savedPost.getUser().getUsername()
                + ". Thank you for creating a post. Your post id is "
                + savedPost.getId();
        emailService.prepareAndSend(savedPost, subject, body);
        return "redirect:/posts";
    }
}
