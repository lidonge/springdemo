package springdemo.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springdemo.users.handlers.UserRepository;
import springdemo.users.models.AppUser;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        AppUser user1 =  userRepository.save(user);
        return user1;
    }

    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable("id") Long id) {
        AppUser user= userRepository.findById(id).orElse(null);
        return user;
    }
}
