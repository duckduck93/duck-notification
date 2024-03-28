package com.duck.notification.message.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Sender<T> {
    private T payload;

    public static <T> Sender<T> from(T value) {
        return new Sender<>(value);
    }
}
