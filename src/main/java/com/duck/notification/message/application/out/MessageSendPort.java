package com.duck.notification.message.application.out;

import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.MessageResult;

public interface MessageSendPort {
    <T> MessageResult send(Message<T> message);
}
