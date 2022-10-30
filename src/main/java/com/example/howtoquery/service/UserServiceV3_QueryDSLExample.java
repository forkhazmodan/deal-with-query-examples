package com.example.howtoquery.service;

import com.example.howtoquery.model.QUser;
import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userServiceV3")
public class UserServiceV3_QueryDSLExample implements UserService {

    public final UserRepository userRepository;

    public UserServiceV3_QueryDSLExample(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> searchUsers(
            String name,
            Integer age,
            Date createdFrom,
            Date createdTo,
            Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(name != null) {
            booleanBuilder.orAllOf(
                    QUser.user.firstName.like("%" + name + "%"),
                    QUser.user.lastName.like("%" + name + "%"));
        }

        if(age != null) {
            booleanBuilder.and(QUser.user.age.goe(age));
        }

        if(createdFrom != null) {
            booleanBuilder.and(QUser.user.createdAt.goe(createdFrom));
        }
//        else {
//            // Default current time
//            booleanBuilder.and(QUser.user.createdAt.goe(new Date(Instant.now().toEpochMilli())));
//        }

        if(createdTo != null) {
            booleanBuilder.and(QUser.user.updatedAt.lt(createdTo));
        }

        return userRepository.findAll(booleanBuilder.getValue(), pageable);
    }

    public User searchUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
