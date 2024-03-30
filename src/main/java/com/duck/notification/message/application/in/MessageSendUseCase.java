package com.duck.notification.message.application.in;

import com.duck.notification.message.domain.Message;

public interface MessageSendUseCase {
    <T> void send(Message<T> message);

}
