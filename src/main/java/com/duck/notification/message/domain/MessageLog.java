package com.duck.notification.message.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageLog<T> {
    private Message<T> message;
    private LocalDateTime sendAt;

    public static <T> MessageLog<T> create(Message<T> message) {
        return new MessageLog<>(message, LocalDateTime.now());
    }
}
