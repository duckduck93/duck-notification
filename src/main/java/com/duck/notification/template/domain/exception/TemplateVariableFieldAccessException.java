package com.duck.notification.template.domain.exception;

public class TemplateVariableFieldAccessException extends RuntimeException {
    public TemplateVariableFieldAccessException(Exception e) {
        super(e);
    }
}
