package com.duck.notification.message.domain;


import com.duck.notification.core.util.IdGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message<T> {
    private Long id;
    private Type type;
    private String title;
    private String content;

    private Sender<T> sender;
    private List<Receiver<T>> receivers;

    public static <T> Message<T> create(Type type, String title, String content, Sender<T> sender, List<Receiver<T>> receivers) {
        return new Message<>(IdGenerator.generate(Long.class), type, title, content, sender, receivers);
    }

    public enum Type {
        EMAIL, SMS, PUSH
    }
}
