package com.duck.notification.template.adapter.persistence;

import com.duck.notification.message.domain.Receiver;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TemplateReceiverJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TemplateJpaEntity template;

    private String receiver;

    @Enumerated(EnumType.STRING)
    private Receiver.Type type;

    public static TemplateReceiverJpaEntity fromReceiver(TemplateJpaEntity templateEntity, Receiver<?> receiver) {
        return new TemplateReceiverJpaEntity(null, templateEntity, receiver.getValue().toString(), receiver.getType());
    }
}
