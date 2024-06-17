package com.crush.user.application.service;

import com.crush.user.adapter.in.web.RequestCreateUser;
import com.crush.user.adapter.out.persistence.UserPersistenceAdapter;
import com.crush.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    public User registerUser(RequestCreateUser command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setPassword(command.getPassword());
        return userPersistenceAdapter.save(user);
    }

    public List<User> getAllUsers() {
        return userPersistenceAdapter.findAll();
    }
}
