package com.duck.notification.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerator {

    private static Long generateLong() {
        return Instant.now().toEpochMilli();
    }

    private static String generateString() {
        return String.valueOf(generateLong());
    }

    public static <T> T generate(Class<T> type) {
        if (type == Long.class) {
            return type.cast(generateLong());
        }
        if (type == String.class) {
            return type.cast(generateString());
        }

        throw new InvalidIdTypeException(type);
    }
}
