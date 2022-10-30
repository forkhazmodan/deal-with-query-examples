package com.example.howtoquery.listener;

import com.example.howtoquery.event.UserCreated;
import com.example.howtoquery.event.UserUpdated;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserUpdatedListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void listen(UserUpdated userUpdated) {
        System.out.println("Old name: " + userUpdated.oldValue.getFirstName());
        System.out.println("New name: " + userUpdated.newValue.getFirstName());
        System.out.println(userUpdated);
    }
}
