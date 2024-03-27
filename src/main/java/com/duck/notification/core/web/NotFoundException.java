package com.duck.notification.core.web;

import org.springframework.http.HttpStatus;

public final class NotFoundException extends ClientException {

    public NotFoundException(Class<?> resource, String id) {
        super(HttpStatus.NOT_FOUND, "[%s](%s) not found".formatted(resource.getSimpleName(), id));
    }

}
