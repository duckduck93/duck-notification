package com.duck.notification.template.domain;

import com.duck.notification.core.util.StringUtil;
import com.duck.notification.template.domain.exception.TemplateVariableFieldAccessException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TemplateVariables {
    private final Map<String, String> variables;

    public TemplateVariables() {
        variables = new HashMap<>();
    }

    public Set<Map.Entry<String, String>> get() {
        return variables.entrySet();
    }

    public void add(String key, String value) {
        variables.put(key, value);
    }

    public void add(Object arg) {
        for (Field field : arg.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                variables.put(field.getName(), StringUtil.defaultString(field.get(arg)));
            } catch (IllegalAccessException e) {
                throw new TemplateVariableFieldAccessException(e);
            }
        }
    }

}
