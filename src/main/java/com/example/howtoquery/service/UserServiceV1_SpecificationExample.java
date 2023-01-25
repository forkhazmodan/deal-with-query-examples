package com.example.howtoquery.service;

import com.example.howtoquery.event.UserCreated;
import com.example.howtoquery.event.UserDeleted;
import com.example.howtoquery.event.UserUpdated;
import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import com.example.howtoquery.specefication.UserSpecification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import org.springframework.transaction.annotation.Transactional;

@Service("userServiceV1")
public class UserServiceV1_SpecificationExample implements UserService {

    public final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserServiceV1_SpecificationExample(UserRepository userRepository, ApplicationEventPublisher eventPublisher
    ) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public Page<User> searchUsers(
            String name,
            Integer age,
            Date createdFrom,
            Date createdTo,
            Pageable pageable) {

         Specification<User> specification = Specification
                 .where(UserSpecification.nameLike(name))
                 .and(UserSpecification.ageGreaterThanOrEqualTo(age))
                 .and(UserSpecification.createdBetween(createdFrom, createdTo))
                 .and(UserSpecification.orderByCreatedAt("DESC"));

        return userRepository.findAll(specification, pageable);
    }

    public User searchUser(Long id) {
        return userRepository.getById(id);
    }

    @Transactional
    public User createUser(User user) {
        User save = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreated(user));
        return save;
    }

    @Transactional
    public User updateUser(User user) {
        User byId = userRepository.getById(user.getId());
        User oldValue = new User(byId);
        byId.setFirstName(user.getFirstName());
        User save = userRepository.save(byId);

        eventPublisher.publishEvent(new UserUpdated(oldValue, save));
        return save;
    }

    @Transactional
    public void deleteUser(Long id) {
        User byId = userRepository.findById(id).get();
        userRepository.delete(byId);
        eventPublisher.publishEvent(new UserDeleted(byId));
    }
}
