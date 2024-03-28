package com.duck.notification.message.application.out;

import com.duck.notification.message.domain.MessageLog;

public interface MessageLogPort {
    <T> void log(MessageLog<T> messageLog);
}
