package codeupspring.springblog.services;

import codeupspring.springblog.Models.User;
import codeupspring.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    private final UserRepository userDao;

    public UserService(UserRepository userDao){
        this.userDao = userDao;
    }

    public User loggedInUser(){
       User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return userDao.findById(loggedInUser.getId()).get();
    }
}
