package com.duck.notification.message.application;

import com.duck.notification.message.application.in.MessageRequestUseCase;
import com.duck.notification.message.application.out.MessageRequestPort;
import com.duck.notification.message.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class MessageRequestService implements MessageRequestUseCase {
    private final MessageRequestPort messageRequestPort;

    @Override
    public <T> void request(Message<T> message) {
        messageRequestPort.request(message);
    }
}
