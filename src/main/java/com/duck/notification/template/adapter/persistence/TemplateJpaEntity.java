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

import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TemplateJpaEntity {
    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Message.Type type;

    private String title;
    private String content;
    private Boolean useYn;

    private String sender;
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateReceiverJpaEntity> receivers;

    public static <T extends Template<?>> TemplateJpaEntity fromTemplate(T template) {
        TemplateJpaEntity entity = new TemplateJpaEntity(
                template.getId(), template.getName(), template.getType(), template.getTitle(), template.getContent(), template.getUseYn(),
                template.getSender().getPayload().toString(), Collections.emptyList()
        );
        entity.receivers = template.getReceivers().stream()
                .map(receiver -> TemplateReceiverJpaEntity.fromReceiver(entity, receiver))
                .toList();
        return entity;
    }

    public <T extends Template<?>> T toTemplate() {
        if (type == Message.Type.EMAIL) {
            Sender<Email> emailSender = Sender.from(Email.from(sender));
            List<Receiver<Email>> emailReceivers = receivers.stream()
                    .map(receiver -> Receiver.of(Email.from(receiver.getReceiver()), receiver.getType()))
                    .toList();
            return (T) EmailTemplate.bind(id, name, title, content, useYn, emailSender, emailReceivers);
        }
        throw new UnsupportedOperationException();
    }
}
