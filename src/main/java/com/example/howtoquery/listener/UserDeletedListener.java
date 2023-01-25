package com.example.howtoquery.listener;

import com.example.howtoquery.event.UserDeleted;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserDeletedListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void listen(UserDeleted userDeleted) {
        System.out.println(userDeleted);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @Async
    public void listenBefore(UserDeleted userDeleted) {
        System.out.println(userDeleted);
    }
}
