package com.duck.notification.message.adapter.persistence;

import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.MessageLog;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class MessageLogJpaEntity {
    @Id
    private Long id;
    private Message.Type type;
    private String title;
    private String content;

    private String sender;
    @OneToMany(mappedBy = "messageLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageReceiverLogJpaEntity> receivers;

    private LocalDateTime sendAt;

    public static <T> MessageLogJpaEntity from(MessageLog<T> messageLog) {
        Message<T> message = messageLog.getMessage();
        MessageLogJpaEntity entity = new MessageLogJpaEntity(
                message.getId(), message.getType(), message.getTitle(), message.getContent(),
                message.getSender().toString(), Collections.emptyList(),
                messageLog.getSendAt()
        );
        entity.receivers = message.getReceivers().stream()
                .map(receiver -> MessageReceiverLogJpaEntity.fromReceiver(entity, receiver))
                .toList();
        return entity;
    }
}
