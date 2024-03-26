package com.duck.notification.message.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Receiver<T> {
    private T value;
    private Type type;

    public static <T> Receiver<T> of(T value, Type type) {
        return new Receiver<>(value, type);
    }

    public enum Type {
        TO, CC, BCC
    }
}
