package codeupspring.springblog.controllers;

import codeupspring.springblog.Models.Post;
import codeupspring.springblog.Models.User;
import codeupspring.springblog.repositories.PostRepository;
import codeupspring.springblog.repositories.UserRepository;
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

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
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

    @PostMapping("/posts/delete/{id}")
    public String deletePost( Model model,@PathVariable long id){
          postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm(){
        User user = userDao.getOne(1L);
        System.out.println(user.getEmail());
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "Create a new post";
    }
}
