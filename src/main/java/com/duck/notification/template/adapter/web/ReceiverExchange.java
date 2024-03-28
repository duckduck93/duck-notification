package com.duck.notification.template.adapter.web;

import com.duck.notification.message.domain.Receiver;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ReceiverExchange {
    private final String value;
    private final Receiver.Type type;

    public static ReceiverExchange from(Receiver<?> receiver) {
        return new ReceiverExchange(receiver.getPayload().toString(), receiver.getType());
    }
}
