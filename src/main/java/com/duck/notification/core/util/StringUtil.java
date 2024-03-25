package com.duck.notification.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static String defaultString(Object arg) {
        if (arg == null) return "";
        return arg.toString();
    }
}
