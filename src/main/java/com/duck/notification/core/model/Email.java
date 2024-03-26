package com.duck.notification.core.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "from")
public class Email {
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
