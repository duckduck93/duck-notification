package com.duck.notification.template.application;

import com.duck.notification.core.model.Email;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import com.duck.notification.template.application.out.TemplateManagePort;
import com.duck.notification.template.domain.EmailTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class TemplateManageServiceTests {
    private final TemplateManageService service;
    private final TemplateManagePort port;

    private TemplateManageServiceTests() {
        port = Mockito.mock(TemplateManagePort.class);
        service = new TemplateManageService(port);
    }

    @Test
    void createMailTemplate() {
        EmailTemplate template = EmailTemplate.create(
                "TEMPLATE_ID_001",
                "<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>",
                Sender.from(Email.from("sender@test.com")),
                List.of(Receiver.from(Email.from("receiver@test.com")))
        );
        Mockito.when(port.create(any())).thenReturn(template);

        EmailTemplate created = service.create(template);
        Assertions.assertThat(created).isInstanceOf(EmailTemplate.class);
        Assertions.assertThat(created.getContentVariables()).isEqualTo(List.of("header", "variable1", "variable2"));
    }

}
