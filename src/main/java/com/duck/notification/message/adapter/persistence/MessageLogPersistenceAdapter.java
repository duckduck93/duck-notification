package com.duck.notification.message.adapter.persistence;

import com.duck.notification.core.annotation.Adapter;
import com.duck.notification.message.application.out.MessageLogPort;
import com.duck.notification.message.domain.MessageLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
@RequiredArgsConstructor
class MessageLogPersistenceAdapter implements MessageLogPort {
    private final MessageLogRepository messageLogRepository;

    @Override
    public <T> void log(MessageLog<T> messageLog) {
        messageLogRepository.save(MessageLogJpaEntity.from(messageLog));
    }
}
