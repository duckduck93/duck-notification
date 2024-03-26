package com.duck.notification.template.adapter.web;

import com.duck.notification.core.model.Email;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import com.duck.notification.template.domain.EmailTemplate;
import com.duck.notification.template.domain.Template;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class TemplateExchange {
    private final String id;
    private final String name;
    private final Message.Type type;
    private final String content;
    private final String sender;
    private final List<ReceiverExchange> receivers;

    public <T extends Template<?>> T toTemplate() {
        if (type == Message.Type.EMAIL) {
            Sender<Email> emailSender = Sender.from(Email.from(sender));
            List<Receiver<Email>> emailReceivers = receivers.stream()
                    .map(receiverRequest -> Receiver.of(Email.from(receiverRequest.getValue()), receiverRequest.getType()))
                    .toList();
            return (T) EmailTemplate.create(id, name, content, emailSender, emailReceivers);
        }
        throw new UnsupportedOperationException();
    }

    public static <T extends Template<?>> TemplateExchange from(T template) {
        return new TemplateExchange(
                template.getId(), template.getName(), template.getType(), template.getContent(),
                template.getSender().getValue().toString(),
                template.getReceivers().stream().map(ReceiverExchange::from).toList()
        );
    }
}