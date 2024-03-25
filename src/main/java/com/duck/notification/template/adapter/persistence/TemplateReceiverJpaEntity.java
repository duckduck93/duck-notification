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
public class TemplateReceiverJpaEntity {
    @Id
    private String receiver;

    @Enumerated(EnumType.STRING)
    private Receiver.Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    private TemplateJpaEntity template;

    public static TemplateReceiverJpaEntity fromReceiver(Receiver<?> receiver) {
        return new TemplateReceiverJpaEntity(receiver.getValue().toString(), receiver.getType(), null);
    }
}
