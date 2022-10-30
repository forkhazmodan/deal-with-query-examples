package com.example.howtoquery.event;

import com.example.howtoquery.model.User;
import org.springframework.context.ApplicationEvent;

public class UserCreated extends ApplicationEvent {
    private final User user;

    public UserCreated(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
