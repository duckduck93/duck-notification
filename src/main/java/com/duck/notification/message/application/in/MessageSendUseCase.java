package com.duck.notification.message.application.in;

import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.MessageResult;

public interface MessageSendUseCase {
    <T> MessageResult send(Message<T> message);
}
