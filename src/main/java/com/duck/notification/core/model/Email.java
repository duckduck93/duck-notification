package com.duck.notification.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "from")
public class Email {
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
