package com.duck.notification.message.adapter.web;

import com.duck.notification.message.application.in.MessageRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MessageController {
    private final MessageRequestUseCase messageRequestUseCase;

    @PostMapping("/messages")
    void requestMessage(@RequestBody MessageRequest request) {
        messageRequestUseCase.request(request.toMessage());
    }
}
