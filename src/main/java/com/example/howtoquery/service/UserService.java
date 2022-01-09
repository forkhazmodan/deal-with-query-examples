package com.example.howtoquery.service;

import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import com.example.howtoquery.specefication.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> searchUsers(
            String name,
            Integer age,
            Date createdFrom,
            Date createdTo,
            Pageable pageable) {

         Specification<User> specification = Specification
                 .where(UserSpecification.userNameLike(name))
                 .and(UserSpecification.userAgeGreaterThanOrEqualTo(age))
                 .and(UserSpecification.createdBetween(createdFrom, createdTo));

        return this.searchUsers(specification, pageable);
    }

    public Page<User> searchUsers(Specification<User> userSpecification, Pageable pageable) {
        return userRepository.findAll(userSpecification, pageable);
    }
}
