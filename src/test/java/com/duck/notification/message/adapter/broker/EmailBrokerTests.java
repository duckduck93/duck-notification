package com.duck.notification.message.adapter.broker;


import com.duck.notification.core.model.Email;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmailBrokerTests {

    @Autowired
    private EmailBroker emailBroker;

    @Test
    void mailSendTest() {
        Message<Email> message = Message.create(
                Message.Type.EMAIL, "TEST MAIL TITLE", "TEST MAIL CONTENT",
                Sender.from(Email.from("sender@test.com")),
                List.of(
                        Receiver.of(Email.from("receiver1@test.com"), Receiver.Type.TO),
                        Receiver.of(Email.from("receiver2@test.com"), Receiver.Type.CC),
                        Receiver.of(Email.from("receiver3@test.com"), Receiver.Type.BCC)
                )
        );
        Assertions.assertDoesNotThrow(() -> emailBroker.send(message));
    }
}
