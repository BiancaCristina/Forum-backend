package com.github.biancacristina.Forum.services;

import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
