package com.duck.notification.message.adapter.web;

import com.duck.notification.core.model.Email;
import com.duck.notification.message.application.in.MessageRequestUseCase;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.verify;

class MessageControllerTests {
    private MessageController messageController;
    private MessageRequestUseCase messageRequestUseCase;

    @BeforeEach
    void setUp() {
        messageRequestUseCase = Mockito.mock(MessageRequestUseCase.class);
        messageController = new MessageController(messageRequestUseCase);
    }

    @Test
    void requestMessage() {
        MessageRequest request = new MessageRequest(
                Message.Type.EMAIL, "Mail Message Title", "Mail Message Content",
                "sender@test.com",
                List.of(
                        new ReceiverExchange("receiver1@test.com", Receiver.Type.TO),
                        new ReceiverExchange("receiver2@test.com", Receiver.Type.CC)
                )
        );

        Assertions.assertThatCode(() -> messageController.requestMessage(request))
                .doesNotThrowAnyException();

        ArgumentCaptor<Message<Email>> argumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRequestUseCase).request(argumentCaptor.capture());


        Message<Email> message = argumentCaptor.getValue();
        Assertions.assertThat(message.getTitle()).isEqualTo("Mail Message Title");
        Assertions.assertThat(message.getContent()).isEqualTo("Mail Message Content");

        Assertions.assertThat(message.getSender())
                .usingRecursiveComparison()
                .isEqualTo(Sender.from(Email.from("sender@test.com")));

        Assertions.assertThat(message.getReceivers())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        Receiver.of(Email.from("receiver1@test.com"), Receiver.Type.TO),
                        Receiver.of(Email.from("receiver2@test.com"), Receiver.Type.CC)
                );
    }
}
