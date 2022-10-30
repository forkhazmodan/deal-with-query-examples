package com.example.howtoquery.event;

import com.example.howtoquery.model.User;
import org.springframework.context.ApplicationEvent;

public class UserUpdated extends ApplicationEvent {
    public User oldValue;
    public User newValue;

    public UserUpdated(User oldValue, User newValue) {
        super(newValue);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public User getOldValue() {
        return oldValue;
    }

    public void setOldValue(User oldValue) {
        this.oldValue = oldValue;
    }

    public User getNewValue() {
        return newValue;
    }

    public void setNewValue(User newValue) {
        this.newValue = newValue;
    }
}
