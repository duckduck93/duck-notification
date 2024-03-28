package com.duck.notification.message.application.out;

import com.duck.notification.message.domain.Message;

public interface MessageRequestPort {
    <T> void request(Message<T> message);
}
