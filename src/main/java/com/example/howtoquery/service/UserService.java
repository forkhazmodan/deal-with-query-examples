package com.example.howtoquery.service;

import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import com.example.howtoquery.specefication.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(String name, Integer age, Pageable pageable) {
         Specification<User> specification = Specification
                 .where(UserSpecification.userNameLike(name))
                 .and(UserSpecification.userAgeGreaterThanOrEqualTo(age));

        return this.getUsers(specification, pageable);
    }

    public Page<User> getUsers(Specification<User> userSpecification, Pageable pageable) {
        return userRepository.findAll(userSpecification, pageable);
    }
}
