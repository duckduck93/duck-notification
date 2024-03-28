package com.duck.notification.message.adapter.persistence;

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
class MessageReceiverLogJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MessageLogJpaEntity messageLog;

    private String receiver;

    @Enumerated(EnumType.STRING)
    private Receiver.Type type;

    public static MessageReceiverLogJpaEntity fromReceiver(MessageLogJpaEntity messageLogEntity, Receiver<?> receiver) {
        return new MessageReceiverLogJpaEntity(null, messageLogEntity, receiver.getPayload().toString(), receiver.getType());
    }
}
