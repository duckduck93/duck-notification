package com.duck.notification.template.adapter.persistence;

import com.duck.notification.core.model.Email;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import com.duck.notification.template.domain.EmailTemplate;
import com.duck.notification.template.domain.Template;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateJpaEntity {
    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Message.Type type;

    private String content;
    private String useYn;

    private String sender;
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateReceiverJpaEntity> receivers;

    public static <T extends Template<?>> TemplateJpaEntity fromTemplate(T template) {
        return new TemplateJpaEntity(
                template.getId(), template.getName(), template.getType(), template.getContent(), template.getUseYn(),
                template.getSender().getValue().toString(),
                template.getReceivers().stream().map(TemplateReceiverJpaEntity::fromReceiver).toList()
        );
    }

    public <T extends Template<?>> T toTemplate() {
        return switch (type) {
            case EMAIL -> ((Class<T>) EmailTemplate.class).cast(EmailTemplate.bind(
                    id,
                    name,
                    content,
                    useYn,
                    Sender.from(Email.from(sender)),
                    receivers.stream()
                            .map(TemplateReceiverJpaEntity::getReceiver)
                            .map(Email::from)
                            .map(Receiver::from)
                            .toList()
            ));
            case SMS, PUSH -> throw new UnsupportedOperationException();
        };
    }
}
