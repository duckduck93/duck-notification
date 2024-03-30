package com.duck.notification.message.adapter.web;

import com.duck.notification.core.model.Email;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class MessageRequest {
    private Message.Type type;
    private String title;
    private String content;
    private String sender;
    private List<ReceiverExchange> receivers;

    public Message<?> toMessage() {
        return switch (type) {
            case EMAIL -> Message.create(
                    type, title, content,
                    Sender.from(Email.from(sender)),
                    receivers.stream().map(exchange -> Receiver.of(Email.from(exchange.getValue()), exchange.getType())).toList()
            );

            case SMS, PUSH -> throw new UnsupportedOperationException();
        };
    }
}
