package com.duck.notification.message.application.out;

import com.duck.notification.message.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface MessageSendPort {
    <T> MessageSendResult send(Message<T> message);


    enum Status {
        SUCCESS, FAIL
    }

    @Getter
    @AllArgsConstructor(staticName = "from")
    class MessageSendResult {
        private final Status status;
    }
}
