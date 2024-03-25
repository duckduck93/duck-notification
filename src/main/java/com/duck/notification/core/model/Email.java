package com.duck.notification.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "from")
public class Email {
    private String name;
    private final String value;

    @Override
    public String toString() {
        return "%s(%s)".formatted(name, value);
    }
}
