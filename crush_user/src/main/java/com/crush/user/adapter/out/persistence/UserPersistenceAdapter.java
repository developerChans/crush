package com.crush.user.adapter.out.persistence;

import com.crush.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPersistenceAdapter {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity = userRepository.save(entity);
        user.setId(entity.getId());
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll().stream().map(entity -> {
            User user = new User();
            user.setId(entity.getId());
            user.setUsername(entity.getUsername());
            user.setPassword(entity.getPassword());
            return user;
        }).collect(Collectors.toList());
    }
}
