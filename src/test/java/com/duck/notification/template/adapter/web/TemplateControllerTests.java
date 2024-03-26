package com.duck.notification.template.adapter.web;

import com.duck.notification.core.model.Email;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import com.duck.notification.template.application.in.TemplateManageUseCase;
import com.duck.notification.template.domain.EmailTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.verify;

class TemplateControllerTests {
    private TemplateController templateController;
    private TemplateManageUseCase templateManageUseCase;

    @BeforeEach
    void setUp() {
        templateManageUseCase = Mockito.mock(TemplateManageUseCase.class);
        templateController = new TemplateController(templateManageUseCase);
    }

    @Test
    void createEmailTemplate() {
        TemplateExchange templateCreateRequest = new TemplateExchange(
                "TEMPLATE_ID_001",
                "TEMPLATE_NAME_001",
                Message.Type.EMAIL,
                "<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>",
                "sender@test.com",
                List.of(
                        new ReceiverExchange("receiver@test.com", Receiver.Type.TO),
                        new ReceiverExchange("receiver2@test.com", Receiver.Type.CC)
                )
        );

        templateController.createTemplate(templateCreateRequest);

        ArgumentCaptor<EmailTemplate> argumentCaptor = ArgumentCaptor.forClass(EmailTemplate.class);
        verify(templateManageUseCase).create(argumentCaptor.capture());

        EmailTemplate template = argumentCaptor.getValue();
        Assertions.assertThat(template.getId()).isEqualTo("TEMPLATE_ID_001");
        Assertions.assertThat(template.getName()).isEqualTo("TEMPLATE_NAME_001");
        Assertions.assertThat(template.getContent()).isEqualTo("<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>");

        Assertions.assertThat(template.getSender())
                .usingRecursiveComparison()
                .isEqualTo(Sender.from(Email.from("sender@test.com")));

        Assertions.assertThat(template.getReceivers())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        Receiver.of(Email.from("receiver@test.com"), Receiver.Type.TO),
                        Receiver.of(Email.from("receiver2@test.com"), Receiver.Type.CC)
                );
    }
}
