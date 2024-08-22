package com.srvnn.ProductApplication.service;

import com.srvnn.ProductApplication.entity.User;
import com.srvnn.ProductApplication.model.RegisterRequest;
import com.srvnn.ProductApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Lazy
    @Autowired
    private UserRepository userRepository;

    public UUID registerUser(RegisterRequest request) {
        User user = new User();
        userRepository.save(user);
        return UUID.randomUUID();
    }

}