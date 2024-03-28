package com.duck.notification.message.application.in;

import com.duck.notification.message.domain.Message;

public interface MessageRequestUseCase {
    <T> void request(Message<T> message);
}
