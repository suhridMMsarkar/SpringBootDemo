package com.datasoft.spring_demo.controller;

import com.datasoft.spring_demo.model.User;
import com.datasoft.spring_demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class HomeController {

//    Logger log = LoggerFactory.getLogger("HomeController");

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Java Training";
    }

    @PostMapping("/add/{id}")
    public User getUser(@PathVariable Long id) {
        log.info("Received id: {}", id);
        return new User(id, "Suhrid");
    }

    @PostMapping("/addUser")
    public Boolean addUser(@RequestBody User user) {
        log.info("Received user: {}", user.toString());
        return Boolean.TRUE;
    }

    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userService.getUserList();
    }
}
