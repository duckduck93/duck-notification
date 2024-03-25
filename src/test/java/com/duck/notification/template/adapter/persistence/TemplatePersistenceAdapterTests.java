package com.duck.notification.template.adapter.persistence;

import com.duck.notification.core.model.Email;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import com.duck.notification.template.domain.EmailTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@DataJpaTest
@EnableJpaRepositories
class TemplatePersistenceAdapterTests {
    @Autowired
    private TemplateJpaRepository templateJpaRepository;
    private TemplatePersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new TemplatePersistenceAdapter(templateJpaRepository);
    }

    @Test
    void createEmailTemplate() {
        EmailTemplate template = EmailTemplate.create(
                "TEMPLATE_ID_001",
                "TEMPLATE_NAME_001",
                "<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>",
                Sender.from(Email.from("sender@test.com")),
                List.of(
                        Receiver.from(Email.from("receiver@test.com")),
                        Receiver.from(Email.from("receiver2@test.com"))
                )
        );
        EmailTemplate created = adapter.create(template);
        Assertions.assertThat(created).isInstanceOf(EmailTemplate.class);
        Assertions.assertThat(created.getId()).isEqualTo("TEMPLATE_ID_001");
        Assertions.assertThat(created.getName()).isEqualTo("TEMPLATE_NAME_001");
        Assertions.assertThat(created.getContent()).isEqualTo("<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>");
        Assertions.assertThat(created.getSender().getValue()).hasToString("sender@test.com");
        Assertions.assertThat(created.getReceivers().stream().map(Receiver::getValue).map(Email::toString).toList()).isEqualTo(List.of("receiver@test.com", "receiver2@test.com"));
    }
}
