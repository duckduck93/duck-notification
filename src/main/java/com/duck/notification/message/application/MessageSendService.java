package com.duck.notification.message.application;

import com.duck.notification.message.application.in.MessageSendUseCase;
import com.duck.notification.message.application.out.MessageLogPort;
import com.duck.notification.message.application.out.MessageSendPort;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.MessageLog;
import com.duck.notification.message.domain.MessageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class MessageSendService implements MessageSendUseCase {
    private final MessageSendPort messageSendPort;
    private final MessageLogPort messageLogPort;

    @Override
    public <T> MessageResult send(Message<T> message) {
        MessageResult result = messageSendPort.send(message);
        messageLogPort.log(MessageLog.create(message/*, result*/));
        return result;
    }
}
