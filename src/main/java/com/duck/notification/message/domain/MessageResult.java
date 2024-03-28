package com.duck.notification.message.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResult {
    private boolean success;
    private String message;

    public static MessageResult of(boolean success, String message) {
        return new MessageResult(success, message);
    }
}
