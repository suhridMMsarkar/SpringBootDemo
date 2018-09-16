package com.datasoft.spring_demo.service;

import com.datasoft.spring_demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Java"));
        userList.add(new User(2L, "Training"));
        return userList;
    }
}
