package com.duck.notification.core.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "from")
public class Phone {
    private String value;

    @Override
    public String toString() {
        return value;
    }
}
