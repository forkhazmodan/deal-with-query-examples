package com.example.howtoquery.listener;

import com.example.howtoquery.event.UserCreated;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserCreatedListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void listen(UserCreated userCreated) {
        System.out.println(userCreated);
    }
}
