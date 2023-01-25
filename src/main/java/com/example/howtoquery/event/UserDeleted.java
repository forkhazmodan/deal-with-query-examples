package com.example.howtoquery.event;

import com.example.howtoquery.model.User;
import org.springframework.context.ApplicationEvent;

public class UserDeleted extends ApplicationEvent {
    public User user;

    public UserDeleted(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
