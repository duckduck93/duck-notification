package com.duck.notification.message.adapter.broker;

import com.duck.notification.core.annotation.Adapter;
import com.duck.notification.message.application.out.MessageRequestPort;
import com.duck.notification.message.application.out.MessageSendPort;
import com.duck.notification.message.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
@RequiredArgsConstructor
class MessageDirectAdapter implements MessageRequestPort, MessageSendPort {
    private final EmailBroker emailBroker;

    @Override
    public <T> void request(Message<T> message) {
        this.send(message);
    }

    @Override
    public <T> MessageSendResult send(Message<T> message) {
        if (message.getType() == Message.Type.EMAIL) {
            emailBroker.send(message);
            return MessageSendResult.from(Status.SUCCESS);
        }
        throw new UnsupportedOperationException();
    }
}
