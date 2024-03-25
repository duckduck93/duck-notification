package com.duck.notification.core.util;

public class InvalidIdTypeException extends RuntimeException {

    public InvalidIdTypeException(Class<?> invalidClass) {
        super("[%s] is Invalid Id Type".formatted(invalidClass.getName()));
    }
}
