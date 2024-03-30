package com.duck.notification.message.adapter.broker;

import com.duck.notification.core.annotation.Adapter;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.function.Consumer;

@Slf4j
@Adapter
@RequiredArgsConstructor
class EmailBroker {
    private final JavaMailSender javaMailSender;

    public <T> void send(Message<T> message) {
        SimpleMailMessage mailMessage = parseMessage(message);
        javaMailSender.send(mailMessage);
    }

    private static <T> SimpleMailMessage parseMessage(Message<T> message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(message.getTitle());
        mailMessage.setText(message.getContent());
        mailMessage.setFrom(message.getSender().getPayload().toString());
        for (Receiver<T> receiver : message.getReceivers()) {
            Consumer<String> receiverSetter = switch (receiver.getType()) {
                case TO -> mailMessage::setTo;
                case CC -> mailMessage::setCc;
                case BCC -> mailMessage::setBcc;
            };
            receiverSetter.accept(receiver.getPayload().toString());
        }
        return mailMessage;
    }

}
