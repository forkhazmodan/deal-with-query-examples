package com.example.howtoquery.service;

import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userServiceV2")
public class UserServiceV2 implements UserService {

    public final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> searchUsers(
            String name,
            Integer age,
            Date createdFrom,
            Date createdTo,
            Pageable pageable) {

        return userRepository.searchUsers(name, age, createdFrom, createdTo, pageable);
    }
}
