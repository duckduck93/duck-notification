package com.duck.notification.template.adapter.web;

import com.duck.notification.core.web.ClientException;
import com.duck.notification.core.web.ServerException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice(basePackages = {"com.duck.notification.template.adapter.web"})
class TemplateControllerAdvice {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ExceptionResponse> handleClientException(ClientException exception) {
        ExceptionResponse response = ExceptionResponse.of(exception.getStatus().value(), exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ExceptionResponse> handleServerException(ServerException exception) {
        log.error("ServerException", exception);
        ExceptionResponse response = ExceptionResponse.of(exception.getStatus().value(), exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    static class ExceptionResponse {
        private final int status;
        private final String message;
        private final LocalDateTime time;
    }
}
