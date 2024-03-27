package com.duck.notification.core.web;

import org.springframework.http.HttpStatus;

public class ClientException extends ApiException {

    public ClientException(HttpStatus status, String message) {
        super(status, message);
    }
}
