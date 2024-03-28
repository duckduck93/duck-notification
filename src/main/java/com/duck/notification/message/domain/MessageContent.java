package com.duck.notification.message.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageContent {
    private Message.Type type;
    private String title;
    private String body;

    public static MessageContent create(Message.Type type, String title, String content) {
        return new MessageContent(type, title, content);
    }
}
