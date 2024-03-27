package com.duck.notification.core.web;

import org.springframework.http.HttpStatus;

public class ServerException extends ApiException {

    public ServerException(HttpStatus status, String message, Throwable cause) {
        super(status, message, cause);
    }
}
