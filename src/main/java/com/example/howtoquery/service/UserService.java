package com.example.howtoquery.service;

import com.example.howtoquery.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface UserService {
    Page<User> searchUsers(
            String name,
            Integer age,
            Date createdFrom,
            Date createdTo,
            Pageable pageable);

    User searchUser(Long id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
